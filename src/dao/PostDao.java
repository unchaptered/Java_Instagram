package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.PostDto;
import dto.UserDto;
import singleton.Connector;
import singleton.Session;

public class PostDao {
    Connection connector;
    PreparedStatement state;
    ResultSet resultSet;

    /**@author권승연
     * @param String newPost
     * @param String postOwner
     * @return boolean true or false
     */
    public boolean createPost(PostDto newPost, String postOwner) {
        String sql = "INSERT INTO post(post_text,post_tags,post_time,post_owner) VALUES(?,?,?,?);";
        int result=0;
        connector=Connector.getConnection();
        try {
            state=connector.prepareStatement(sql);
            state.setString(1, newPost.getPostText());
            state.setString(2, newPost.getHashtag());
            state.setString(3, newPost.getPostTime());
            state.setString(4, postOwner);
            
            result=state.executeUpdate();
        }catch(SQLException sqle) {
            System.out.println("PostDao.java | create | ������ ����  : "+sqle);
        }
        return result==1;
    }
    
    /**@author 이민석
     * @param String postCode
     * @return PostDto postDto 혹은 null
     */
    public PostDto getPostByCode(String postCode) {
    	String sql="SELECT * FROM post WHERE post_code=?;";
    	
    	connector=Connector.getConnection();
    	PostDto postDto=null;
    	try {
			state=connector.prepareStatement(sql);
			state.setString(1, postCode);
			resultSet=state.executeQuery();
			
			if(resultSet.next()) {
				postDto=new PostDto(
						resultSet.getString("post_text")
			    );
				postDto.setPostTags(resultSet.getString("post_tags"));
				postDto.setPostTime(resultSet.getString("post_time"));
				postDto.setPostCode(resultSet.getString("post_code"));
				postDto.setPostOwner(resultSet.getString("post_owner"));
				return postDto;
			}
		} catch (SQLException sqle) {
            System.out.println("PostDao.java | getPostByCode | ������ ����  : "+sqle);
		}
    	return null;
    }
    
    /**@author 김명현
     * @param int postCode
     * @return String likeList 혹은 null
     */
    public String getLikeList(int postCode) {
    	String sql ="SELECT post_like_list FROM post WHERE post_code=?;";
    	
    	connector=Connector.getConnection();
    	String likeList="";
    	try {
    		state=connector.prepareStatement(sql);
    		state.setString(1, postCode+"");
    		resultSet=state.executeQuery();
    		
    		if(resultSet.next()) {
    			likeList=resultSet.getString("post_like_list");
    		}
        	return likeList;
    	}catch(SQLException sqle) {
            System.out.println("PostDao.java | getLikeList.method | ������ ����  : "+sqle);
    	}
    	return null;
    }

    /**@author 김명현
     * @param int postCode
     * @param String likeList
     * @return boolean true 혹은 false
     */
    public boolean setLikeList(int postCode, String likeList) {
    	String sql="UPDATE post SET post_like_list=? WHERE post_code=?;";
    	
    	connector=Connector.getConnection();
    	try {
    		state=connector.prepareStatement(sql);
    		state.setString(1, likeList);
    		state.setString(2, postCode+"");
    		int success=state.executeUpdate();
    		
    		return (success!=0);
    	}catch(SQLException sqle) {
            System.out.println("PostDao.java | setLikeList | ������ ����  : "+sqle);
    	}
    	return false; 
    }


    /**@author 권승연
     * @param String text
     * @param String userCode
     * @param String postCode
     * @return boolean true 혹은 false
     */
    public boolean updatePostText (String text, String userCode, String postCode) {
    	String sql = "UPDATE post SET post_text = ? WHERE post_owner = ? AND post_code = ?;";
        int result=0;
        connector = Connector.getConnection();
        try {
            state = connector.prepareStatement(sql);
            state.setString(1, text);
            state.setString(2, userCode);
            state.setString(3, postCode);

            result=state.executeUpdate();
        }catch(SQLException sqle) {
            System.out.println("PostDao.java | updatePostText.method | ������ ����  : "+sqle);
        }
        return result==1;
    }
    
    /**@param String tags
     * @param String userCode
     * @param String postCode
     * @return boolean true 혹은 false
     */
    public boolean updatePostTags (String tags, String userCode, String postCode) {
    	String sql = "UPDATE post SET post_tags = ? WHERE post_owner = ? AND post_code = ?;";
        int result=0;
        connector = Connector.getConnection();
        try {
            state = connector.prepareStatement(sql);
            state.setString(1, tags);
            state.setString(2, userCode);
            state.setString(3, postCode);

            result=state.executeUpdate();
        }catch(SQLException sqle) {
            System.out.println("PostDao.java | updatePostTags.method | ������ ����  : "+sqle);
        }
        return result==1;
    }
    
    /**@author 권승연
     * @param String texts
     * @param String userCode
     * @return boolean true 혹은 false
     */
    public boolean updatePostAll (String texts[], String userCode, String postCode) {
    	String sql = "UPDATE post SET post_text = ?, post_tags = ? WHERE post_owner = ? AND post_code = ?;";
    	
    	connector = Connector.getConnection();
    	int result=0;
    	try {
    		state = connector.prepareStatement(sql);
    		state.setString(1, texts[0]);
    		state.setString(2, texts[1]);
    		state.setString(3, userCode);
            state.setString(4, postCode);
    		
    		result = state.executeUpdate();
    	}catch(SQLException sqle) {
            System.out.println("PostDao.java | updatePostTags.method | ������ ����  : "+sqle);
        }
    	return result==1;
    }

    /**@author 권승연
     * @param String postCode
     * @return boolean true 혹은 false
     */
    public boolean deletePost(String postCode) {
        String sql = "DELETE FROM post WHERE post_owner = ? AND post_code = ?;";
        int result=0;
        connector = Connector.getConnection();
        try {
            state = connector.prepareStatement(sql);
            UserDto userDto=(UserDto)Session.get("loggedInUser");
            String userId=userDto.getUserId();

            state.setString(1, userId);
            state.setString(2, postCode);

            result=state.executeUpdate();
        }catch(SQLException sqle) {
            System.out.println("PostDao.java | deletePost.method | ������ ����  : "+sqle);
        }
        return result==1;
    }
}
