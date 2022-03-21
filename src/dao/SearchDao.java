package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PostDto;
import dto.UserDto;
import singleton.Connector;

public class SearchDao {
	Connection connector;
	PreparedStatement state;
	ResultSet resultSet;
	
	/** 유저 아이디로 유저 찾기 <br>
	 * @author 이민석
	 * @param String userId
	 * @return ArrayList<UserDto> userList 혹은 null
	 */
	public ArrayList<UserDto> searchUserById(String userId) {
		String sql="SELECT * FROM user WHERE user_id LIKE ? ORDER BY user_code DESC LIMIT 100;";
		
		connector=Connector.getConnection();
		ArrayList<UserDto> userList=new ArrayList<>(100); 
		userList.trimToSize();
		try {
			state=connector.prepareStatement(sql);
			state.setString(1, "%"+userId+"%");
			resultSet=state.executeQuery();
			
			Loop1 : while(true) {
				if(resultSet.next()) {
					UserDto userDB=new UserDto(
							resultSet.getString("user_id"),
							resultSet.getString("user_email"),
							resultSet.getString("user_pw"),
							resultSet.getString("user_nick"),
							resultSet.getString("user_phone")
		            );
					userDB.setUserCode(resultSet.getString("user_code"));
					userList.add(userDB);
					continue Loop1;
				}
				break Loop1;
			}
			return userList;
		} catch (SQLException sqle) {
			System.out.println("SearchDao.java | searchUserById.method | "+sqle);
		}
		return null;
	}
	
    /** 포스트 내용으로 포스트 찾기<br>
	 * @author 이민석
	 * @param String postText
	 * @return ArrayList<PostDto> postList 혹은 null
	 */
	public ArrayList<PostDto> searchPostByTexts(String postText) {
		String sql="SELECT * FROM post WHERE post_text LIKE ? ORDER BY post_code DESC LIMIT 100;";
		
		connector=Connector.getConnection();
		ArrayList<PostDto> postList=new ArrayList<>(100);
		postList.trimToSize();
		try {
			state=connector.prepareStatement(sql);
			state.setString(1, "%"+postText+"%");
			resultSet=state.executeQuery();
			
			Loop1 : while(true) {
				if(resultSet.next()) {					
					PostDto postDto=new PostDto(resultSet.getString("post_text"));
					postDto.setPostTags(resultSet.getString("post_tags"));
					postDto.setPostTime(resultSet.getString("post_time"));
					postDto.setPostCode(resultSet.getString("post_code"));
					postDto.setPostOwner(resultSet.getString("post_owner"));
					postList.add(postDto);
					continue Loop1;
				}
				break Loop1;
			}
			return postList;
		}catch(SQLException sqle){
			System.out.println("SearchDao.java | searchPostByTexts | ������ ���� : "+sqle);
		}
		return null;
	}
	
	/** 포스트 해시태그로 포스트 찾기<br>
	 * ArrayList<PostDto> 와 관련하여 null 비검사 경고문이 발생하였고 이를 조치하였다.
	 * @author 권혜진
	 * @param String postTarget
	 * @return ArrayList<PostDto> postList 혹은 null
	 */
	public ArrayList<PostDto> searchPostByHashtags(String postTarget){
        String sql="SELECT * FROM post WHERE post_tags LIKE ? ORDER BY post_code DESC LIMIT 100;"; 
        
        connector=Connector.getConnection();
		ArrayList<PostDto> postList=new ArrayList<>(100);
		postList.trimToSize();
		try {
			state=connector.prepareStatement(sql);
			state.setString(1, "%"+postTarget+"%");
			resultSet=state.executeQuery();

			Loop1 : while(true) {
				if(resultSet.next()) {					
					PostDto postDto=new PostDto(resultSet.getString("post_text"));
					postDto.setPostTags(resultSet.getString("post_tags"));
					postDto.setPostTime(resultSet.getString("post_time"));
					postDto.setPostCode(resultSet.getString("post_code"));
					postDto.setPostOwner(resultSet.getString("post_owner"));
					postList.add(postDto);
					continue Loop1;
				}
				break Loop1;
			}
			return postList;
			
		}catch(SQLException sqle){
			System.out.println("SearchDao.java | searchPostByTexts | ������ ���� : "+sqle);
		}
		return null;
    }

	/** 내 아이디값으로 소유한 포스트 찾기
	 * ArrayList<PostDto> 와 관련하여 null 비검사 경고문이 발생하였고 이를 조치하였다.
	 * @author 권승연
	 * @param String ownerCode
	 * @return ArrayList<PostDto> postList 혹은 null
	 */
	public ArrayList<PostDto> searchPostByOwnerCode(String ownerCode){
		String sql="SELECT * FROM post WHERE post_owner=? ORDER BY post_code DESC LIMIT 100;";
		
		connector=Connector.getConnection();
		ArrayList<PostDto> postList=new ArrayList<>(100);
		postList.trimToSize();
		try {
			state=connector.prepareStatement(sql);
			state.setString(1, ownerCode);
			resultSet=state.executeQuery();
			
			Loop1 : while(true) {
				if(resultSet.next()) {					
					PostDto postDto=new PostDto(resultSet.getString("post_text"));
					postDto.setPostTags(resultSet.getString("post_tags"));
					postDto.setPostTime(resultSet.getString("post_time"));
					postDto.setPostCode(resultSet.getString("post_code"));
					postDto.setPostOwner(resultSet.getString("post_owner"));
					postList.add(postDto);
					continue Loop1;
				}
				break Loop1;
			}
			return postList;
		}catch(SQLException sqle){
			System.out.println("SearchDao.java | searchPostByTexts | ������ ���� : "+sqle);
		}
		return null;
	}
}
