package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.UserDto;
import singleton.Connector;

public class UserDao {
	// ResultSet 인터페이스 https://codedragon.tistory.com/5975
	// PreparedStatement 클래스 https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=heoguni&logNo=130170559135
	
	// 자원 반납 해야하는 이유  https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=nuberus&logNo=221438918706
	Connection connector;
	PreparedStatement state;
	ResultSet resultSet;

	/**@author 전원
	 * @param UserDto userDto
	 * @return UserDto userDto 혹은 null
	 */
	public UserDto login(UserDto userDto) {
		String sql="SELECT * FROM user WHERE user_id=? AND user_pw=?";
		
		connector=Connector.getConnection();
		try {
			state=connector.prepareStatement(sql);
			state.setString(1,userDto.getUserId());
			state.setString(2,userDto.getUserPw());
			resultSet=state.executeQuery();
			
			if(resultSet.next()) {
				UserDto userDB=new UserDto(
						resultSet.getString("user_id"),
						resultSet.getString("user_email"),
						resultSet.getString("user_pw"),
						resultSet.getString("user_nick"),
						resultSet.getString("user_phone")
	            );
				userDB.setUserCode(resultSet.getString("user_code"));
				System.out.println(userDB);
				return userDB;
			}
		}catch(SQLException sqle) {
			System.out.println("UserDao.java | login.method | ������ ���� : "+sqle);
		}
		return null;
	}
	
	/**@author 전원
	 * @param UserDto newUser
	 * @return boolean true 혹은 false
	 */
	public boolean join(UserDto newUser) {
		String sql="INSERT INTO user (user_id,user_email,user_pw,user_nick,user_phone) VALUES (?, ?, ?, ?, ?);";
		/* user ���̺� �޼�����
		 */
		int result=0;
		connector=Connector.getConnection();
		try {
			state=connector.prepareStatement(sql);
			// 1�� �ڸ��� (sql) Serial Ÿ�� user_code���� �ڵ����� ���� �Ҵ�޴´�.
			state.setString(1, newUser.getUserId());
			state.setString(2, newUser.getUserEmail());
			state.setString(3, newUser.getUserPw());
			state.setString(4, newUser.getUserNick());
			state.setString(5, newUser.getUserPhone());
			
			result=state.executeUpdate();
		}catch(SQLException sqle) {
			System.out.println("UserDao.java | join | ������ ����  : "+sqle);
		}
		return result==1;
	}
	
	/**@author 전원
	 * @param String userId
	 * @return boolean true 혹은 false
	 */
	public boolean userExistById(String userId) {
		String sql="SELECT * FROM user WHERE user_id=?;";
		
		int result=0;
		connector=Connector.getConnection();
		try {
			state=connector.prepareStatement(sql);
			
			state.setString(1,userId);
			resultSet=state.executeQuery();
			
			return resultSet.next();
		}catch(SQLException sqle) {
			System.out.println("UserDao.java | userExistById.method | ������ ���� : "+sqle);
		}
		
		return result==1;
	}
	
	/**@author 전원
	 * @param String userPhone
	 * @return UserDto userdto 혹은 null
	 */
	public UserDto findUserDtoByPhone(String userPhone) {
		String sql="SELECT * FROM user WHERE user_phone=?;";
		
		connector=Connector.getConnection();
		
		try {
			state=connector.prepareStatement(sql);
			state.setString(1, userPhone);
			resultSet=state.executeQuery();
			
			if(resultSet.next()) {
				UserDto userDB=new UserDto(
						resultSet.getString("user_id"),
						resultSet.getString("user_email"),
						resultSet.getString("user_pw"),
						resultSet.getString("user_nick"),
						resultSet.getString("user_phone")
			    );
				userDB.setUserCode(resultSet.getString("user_code"));
				return userDB;
			}
		} catch (SQLException sqle) {
			System.out.println("UserDao.java | findUserDtoById.method | ������ ���� : "+sqle);
		}
		return null;
	}

	/**@author 전원
	 * @param String userId
	 * @return UserDto userdto 혹은 null
	 */
	public UserDto findUserDtoById(String userId) {
		String sql="SELECT * FROM user WHERE user_id=?;";
		
		connector=Connector.getConnection();
		
		try {
			state=connector.prepareStatement(sql);
			state.setString(1, userId);
			resultSet=state.executeQuery();
			
			if(resultSet.next()) {
				UserDto userDB=new UserDto(
						resultSet.getString("user_id"),
						resultSet.getString("user_email"),
						resultSet.getString("user_pw"),
						resultSet.getString("user_nick"),
						resultSet.getString("user_phone")
			    );
				userDB.setUserCode(resultSet.getString("user_code"));
				return userDB;
			}
		} catch (SQLException sqle) {
			System.out.println("UserDao.java | findUserDtoById.method | ������ ���� : "+sqle);
		}
		return null;
	}

	/**@author 이민석
	 * @param String userPhone
	 * @return String userId 혹은 null
	 */
	public String findUserIdByPhone(String userPhone) {
		String sql="SELECT user_id FROM user WHERE user_phone=?;";
		
		connector=Connector.getConnection();
		
		try {
			state=connector.prepareStatement(sql);
			state.setString(1, userPhone);
			resultSet=state.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getString("user_id");
			}
		} catch (SQLException sqle) {
			System.out.println("UserDao.java | findUserIdByPhone.method | ������ ���� : "+sqle);
		}
		return null;
	}
	
	/**@author 이민석
	 * @param String userPw
	 * @return String userPw 혹은 null
	 */
	public String findUserPwById(String userId) {
		String sql="SELECT user_pw FROM user WHERE user_id=?";
		
		try {
			connector=Connector.getConnection();
			
			state=connector.prepareStatement(sql);
			state.setString(1, userId);
			resultSet=state.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getString("user_id");
			}
		} catch (SQLException sqle) {
			System.out.println("UserDao.java | findUserPwById.method | ������ ���� : "+sqle);
		}
		return null;
	}
	
	/**@author 이민석
     * @param String usreId
     * @param String userPw
     * @return boolean true 혹은 false
     */
    public boolean deleteUser(String usreId, String userPw) {
        String sql = "DELETE FROM user WHERE user_id = ? AND user_pw = ?;";
        int result=0;
        connector = Connector.getConnection();
        try {
            state = connector.prepareStatement(sql);

            state.setString(1, usreId);
            state.setString(2, userPw);

            result=state.executeUpdate();
        }catch(SQLException sqle) {
            System.out.println("UserDao.java | deleteUser.method | ������ ����  : "+sqle);
        }
        return result==1;
    }
}
