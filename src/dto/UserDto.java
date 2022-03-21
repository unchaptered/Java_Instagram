package dto;

/**@author 고결
 */
public class UserDto {
	
	// 기본키 필드
	private String userCode=null;
	
	// 필수값 필드
	private String userId;
	private String userEmail;
	private String userPw;
	private String userNick;
	private String userPhone;
	
	// 선택값 필드
	private String userBirth;
	private String userPostList;

	/**로그인 패키징 용
	 * @author 고결
	 * @param String userId
	 * @param String userPw
	 */
	public UserDto(String userId, String userPw) {
		this.userId = userId;
		this.userPw = userPw;
	}

	/**객체 생성용
	 * @param userId, userEmail, userPw, userNick, userPhone
	 */
	public UserDto(String userId, String userEmail, String userPw, String userNick, String userPhone) {
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPw = userPw;
		this.userNick = userNick;
		this.userPhone = userPhone;
	}

	/** @author 고결
	 */
	@Override public String toString() {
		if(userCode==null) {
			return userId + "\t" + userEmail + "\t" + userPw + "\t"; 
		}else{
			return userCode + "\t" + userId + "\t" + userEmail + "\t" + userPw + "\t";
		}
	}
	
	
	/** @author 이민석
	 */
	@Override public boolean equals(Object obj) {
		boolean check=false;
		
		if(!(obj instanceof UserDto)) {
			return check;
		}
		UserDto userDto=(UserDto) obj;
		// join �ÿ��� userCode �� null �������� �� ���� ���� ���� �ִ�.
		if(userCode!=null) {	
			if(userCode.equals(userDto.getUserCode())) check=true;
		}
		
		if(!userId.equals(userDto.getUserId())) return check=false;
		if(!userEmail.equals(userDto.getUserEmail())) return check=false;
		if(!userPw.equals(userDto.getUserPw())) return check=false;
		if(!userNick.equals(userDto.getUserNick())) return check=false;
		if(!userPhone.equals(userDto.getUserPhone())) return check=false;

		return check=true;
	}
	
	/** @author 고결
	 */
	@Override public int hashCode() {
        int hash = 1;
        hash = 31 * hash + (userCode == null ? 0 : userCode.hashCode());
        hash = 31 * hash + userId.hashCode();
        hash = 31 * hash + (userEmail == null ? 0 : userEmail.hashCode());
        hash = 31 * hash + userPw.hashCode();
        hash = 31 * hash + (userNick == null ? 0 : userNick.hashCode());
        hash = 31 * hash + (userPhone == null ? 0 : userPhone.hashCode());
        return hash;
    }

	// Getters

	public String getUserId() {
		return userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public String getUserPw() {
		return userPw;
	}
	public String getUserNick() {
		return userNick;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public String getUserCode() {
		return userCode;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public String getUserPostList() {
		return userPostList;
	}

	// Setters

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public void setUserPostList(String userPostList) {
		this.userPostList = userPostList;
	}
}
