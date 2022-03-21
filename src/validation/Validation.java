package validation;

public class Validation {
	/**@param String s
	 * @return boolean true 혹은 false | true 는 공백값이라는 뜻이다.
	 */
	public static boolean checkBlankSpace(String s) {
		if(s.equals("")) {
			return true;
		}
		return false;
	}
	
	/**@param String s
	 * @return boolean true 혹은 false | true 는 통과했다는 뜻이다.
	 */
	public static boolean checkIdLength(String s) {
		if(s.length()>=8) {
			return true;
		}
		return false;
	}
	
	/**@author 권혜진
	 * @param String hashtags
	 * @return String hashtags 
	 */
	public static String checkHashtags(String hashtags) {
		String[] tags = {};
        String result = "";
        
        tags = hashtags.split("[ \\,\\.\\/\\-\\#]");
        for (int i = 0; i < tags.length; i++) {
            if(!tags[i].equals("")) {
                if(i != tags.length ){
                    result += "#";
                }
            result += tags[i];
            }
        }
        return result;
	}
	
	/**정규식 ^([(A-Z){1,}(a-z0-9){1,}(\\W){1,}]){8,16}
	 * @author 고결
	 * @param String s
	 * @return true 는 유효성 검사를 통과했다는 뜻이다.
	 */
	public static boolean checkPwRegex(String s) {
		return (s.matches("^([(A-Z){1,}(a-z0-9){1,}(\\W){1,}]){8,16}")) ? true : false;
	}
	
	/**정규식 ^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$"
	 * @param String s
	 * @return true 는 유효성 검사를 통과했다는 뜻이다.
	 */
	public static boolean checkPhoneRegex(String s) {
		return (s.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")) ? true : false;
	}
}