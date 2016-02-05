package org.ivan.MojRad.classes;
import java.util.ArrayList;

import org.ivan.MojRad.DaoClasses.UserDAO;

public abstract class Box {
	
	// definicija metode za box suggestion friends
	public static String suggFriends(ArrayList<User> u,int UserReqID){
		String htmlString="<div id=\"suggfriends\">"
						+"<p>Suggestion friends...</p>"
						+"<ul>";
		if(u!=null && u.size()>0){
			for(User pom : u){
				htmlString+="<li><a href='Profile?opt=profile&userid="+pom.getUserID()+"' target='_blank'>"
						+"<img src="+pom.getProfilePicture()+"><span>"+pom.getFirstName()+" "+pom.getLastName()+"</span></a>"
						+"<form action='SendRequest' method='post'>"
						+"<input type=\"submit\" class=\"accept\" value=\"Add\">"
						+"<input type=\"hidden\" name='UserRespID' value="+pom.getUserID()+" >"
						+"<input type=\"hidden\" name='UserReqID' value="+UserReqID+" >"
					+"</form></li>";
			}
			htmlString+="</ul></div>";
		} else {
			htmlString+="<p>No available suggestions now...</p></ul></div>";
		}
		return htmlString;
	}
	
	//online friends
	public static String onlineFriends(ArrayList<User> u){
		String htmlString="<div id=\"suggfriends\">"
						+"<p>Online friends...</p>"
						+"<ul>";
		if(u!=null && u.size()>0){
			
			for(User pom : u){
				htmlString+="<li>"
						+"<img src="+pom.getProfilePicture()+"><span><a href='Profile?opt=message&userid="+pom.getUserID()+"'>"+pom.getFirstName()+" "+pom.getLastName()+"</a></span>"
						+"<img src='images/online/online.jpg'>"
						+"<input type=\"hidden\" name='UserRespID' value="+pom.getUserID()+" >";
			}
			htmlString+="</ul></div>";
		} else {
			htmlString+="<p>No available friends now...</p></ul></div>";
		}
		return htmlString;
	}
	
	
	//messages all
	public static String allMessages(String u[], User me){
		String html="";
		UserDAO user= new UserDAO();
		User pom;
			if(u!=null && u.length>0){
				
				pom=user.getUserbyID(Integer.parseInt(u[10].toString()));
				String notiff="";
			
				if(Validate.check(u[9].toString(), "t") && me.getUserID()==Integer.parseInt(u[8].toString()) )
					notiff="(1)";
				
				html="<div class=\"singlemessages\">"
						+"<div>"
							+"<img  src=\""+pom.getProfilePicture()+"\">"
							+"<p>"+pom.getFirstName()+" "+pom.getLastName()+"</p>"
							+"<span class='notiff'>"+notiff+"</span>"
							+"<input type=\"button\" value=\"Open\" class='openMess'>"
							+"</div>"
						+"<div class=\"messagesdetails\">"
							+"<textarea class='oldMessage' disabled>"+u[5].toString()+"</textarea>"
							+"<textarea  placeholder=\"...type here to replay...\" class='replaytext'></textarea>"
							+"<span class='time'>Last time: "+ u[6].toString().subSequence(0, 16)+"</span>"
							+"<input type=\"button\" value=\"Replay\" class='replay'>"
							+"<input type=\"hidden\" class='userID' value=\""+u[10].toString()+"\">"
							+"<input type=\"hidden\" class='messageID' value=\""+u[4].toString()+"\">"
							+"<input type=\"hidden\" class='nottiID' value=\""+u[7].toString()+"\">"
							+"<input type=\"hidden\" class='myname' value=\""+me.getFirstName()+"\">"
							+"</div>"
					+"</div>";
					
			}
				
		return html;
	}
	
}
