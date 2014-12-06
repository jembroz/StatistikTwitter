package com.s3.project.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.IDs;
import IndonesianNLP.IndonesianNETagger;
import IndonesianNLP.IndonesianSentenceFormalization;

import com.s3.project.bean.BioTaggerBean;
import com.s3.project.bean.UserTwitterBean;
import com.s3.project.connection.ConnectionManager;
import com.s3.project.dao.BioTagDAO;
import com.s3.project.dao.UserTwitterBioDAO;

/**
 * Servlet implementation class ShowUser
 */
@WebServlet("/ShowUserServlet")
public class ShowUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if (request.getParameter("update") != null) {
			//update button is clicked
			//Method untuk mengambil informasi user Twitter
				System.out.println("Listing following ids.");
				Connection currentCon = null;
				currentCon = ConnectionManager.getConnection();
				try {//Ambil ID yang kita following
					currentCon.setAutoCommit(false);
		            Twitter twitter = new TwitterFactory().getInstance();
		            long cursor = -1;
		            IDs ids;
		            System.out.println("Listing following ids.");
		            do {
		                ids = twitter.getFriendsIDs(cursor);
		                
		                for (long id : ids.getIDs()) {
		                    System.out.println(id);//Insert ID ke DB
		                    //User user = twitter.showUser(id); Cara ambil user profile berdasarkan ID.
		                    this.insertIdToUserTwitter(id,currentCon);
		                }
		            } while ((cursor = ids.getNextCursor()) != 0);
		            currentCon.commit();
		        } catch (TwitterException te) {
		            te.printStackTrace();
		            System.out.println("Failed to get friends' ids: " + te.getMessage());
		        }catch (SQLException se){
		        	se.printStackTrace();
		        }
				
				try {
					currentCon.setAutoCommit(false);
					//Query dulu dapeting ID nya dari DB.
					List<UserTwitterBean> usrTwitBean = UserTwitterBioDAO.GetUserBio();
					Twitter twitter = new TwitterFactory().getInstance();
					int i = 0;
					for(UserTwitterBean a : usrTwitBean){
						User user = twitter.showUser(Long.parseLong(a.getId()));
						UserTwitterBean usrReslt = new UserTwitterBean();
						usrReslt.setScreenname(user.getScreenName());
						usrReslt.setName(user.getName());
						usrReslt.setFriends_count(user.getFriendsCount());
						usrReslt.setFollowers_count(user.getFollowersCount());
						usrReslt.setCreated_at(user.getCreatedAt().toString());
						usrReslt.setDescription(user.getDescription());
						usrReslt.setStatuses_count(user.getStatusesCount());
						usrReslt.setId(a.getId());
			            UserTwitterBioDAO.UpdateUser(usrReslt,currentCon);
			            System.out.println("sampai di sini : "+i);
			            i++;
					}
					System.out.println("Commit Pakk");
					currentCon.commit();
					currentCon.close();
		        } catch (TwitterException te) {
		            te.printStackTrace();
		            System.out.println("Failed to delete status: " + te.getMessage());
		        }catch (SQLException se){
		        	se.printStackTrace();
		        }
		 }else if (request.getParameter("posttager") != null) {
	          //posttager button is clicked
			 BufferedReader br = null;
		    	try {
		    		List<UserTwitterBean> usrTwitBean = UserTwitterBioDAO.GetUserBio();
		            for (UserTwitterBean a : usrTwitBean) {
		                //Di formalize dulu
		                
		                IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
		        		String kicauFrml = formalizer.formalizeSentence(a.getDescription().toLowerCase());
		        		String kicauFrmlCln = kicauFrml.replaceAll("[?`~!@#$%^&*()-_+={}|\\/.,><;:'\"â™¥Ç♥]", "");
		        		
		        		File fileInput = new File("inputSrcBio.txt");
		        		File fileOutput = new File("hasilDstBio.arff");
		        		
		        		// if file doesnt exists, then create it
		        		if (!fileInput.exists()) {
		        			fileInput.createNewFile();
		        		}
		        		if (!fileOutput.exists()) {
		        			fileOutput.createNewFile();
		        		}

		        		FileWriter fw = new FileWriter(fileInput.getAbsoluteFile());
		        		BufferedWriter bw = new BufferedWriter(fw);
		        		bw.write(kicauFrmlCln);
		        		bw.close();
		        		
		        		IndonesianNETagger inner = new IndonesianNETagger();
		                inner.NETagFile("inputSrcBio.txt", "hasilDstBio.arff");
		                
		                br = new BufferedReader(new FileReader("hasilDstBio.arff"));
		                String sCurrentLine;
		                int iCounter1 = 1;//Counter untuk setiap baris dalam file
		        		while ((sCurrentLine = br.readLine()) != null) {
		        			System.out.println(sCurrentLine);
		        			BioTaggerBean bioTagBean = new BioTaggerBean();
		        			bioTagBean.setBiotagID(Integer.parseInt(a.getId()));
		        			bioTagBean.setNourut(iCounter1);
		        			
		        			int iCounter2 = 1;//Counter untuk setiap pemisahan kata setelah di split oleh posttagging
		        			for(String retrieval: sCurrentLine.split(",")){
		        				if(iCounter2==1){
		        					bioTagBean.setDeskripsi(retrieval);//Deskripsi
		        				}else if(iCounter2==2){
		        					bioTagBean.setJenisDesc(retrieval);//Jenis Deskripsi
		        				}else{
		        					bioTagBean.setKategori(retrieval);//Kategori
		        				}
		        				iCounter2++;
		        			}
		        			Date date = new Date();
		        			bioTagBean.setTanggal(new Timestamp(date.getTime()));
		        			Boolean bioTagDAO = BioTagDAO.InsertBioTagger(bioTagBean);
		        			System.out.println("Insert BioTagger : " + bioTagBean);
		        			iCounter1++;
		        		}		                
		            }
		        } catch (IOException ioe) {
		            ioe.printStackTrace();
		            System.out.println("Failed to store tweets: " + ioe.getMessage());
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Failed to store tweets: " + e.getMessage());
		        }		    	finally {
					try {
						if (br != null)br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
	    }
	}
	
	private void insertIdToUserTwitter(Long userID, Connection currentCon){
		UserTwitterBean userTwitterBean = new UserTwitterBean();
		userTwitterBean.setId(String.valueOf(userID));
		userTwitterBean.setName("");
		userTwitterBean.setScreenname("");
		userTwitterBean.setStatuses_count(0);
		userTwitterBean.setDescription("");
		userTwitterBean.setFollowers_count(0);
		userTwitterBean.setFriends_count(0);
		userTwitterBean.setCreated_at("");
		UserTwitterBioDAO.CreateUser(userTwitterBean,currentCon);
	}

}
