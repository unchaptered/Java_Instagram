package view;

import dto.UserDto;
import singleton.Scan;

public class UserView {
	/**해당 로직을 참고하여 Index.java - indexView() 에서 SQL 과 연결하여 구현하였다.
	 * @deprecated 
	 * @author 권혜진
	 */
	public void findId() {
		String user="권혜진";
		String userid="hyejin97";
		String phone="010-1234-1234";

		// 아이디 찾기
		// > 핸드폰 번호 일치 3회 제한
		// > 질문
		int count = 1;
		
		String result="";
		while(count<=3) {
			System.out.print("핸드폰번호 입력 : ");
			String inputPhone = Scan.scanString();

			if (inputPhone.equals(phone)) {
				System.out.println("[ " + user + " 님 환영합니다. ]");
				
				while(true) {
					System.out.println("제일 멋진 조는 ?\n1.1조\n2.2조\n3.3조\n4.4조");
					int choice = Scan.scanInt();
					if(choice != 4) {
						System.out.println("다시 생각해보세요.");
						continue;
					}
					result=userid;
					System.out.println("정답입니다!");
					System.out.println("당신의 아이디는 " + result + " 입니다.");
					break;
				}
				break;
			} else {
				System.out.println("[ 핸드폰 번호가 틀려요! ]");
				count++;
			}
		}
		if (count == 4) {
			System.out.println("[ 3회 모두 틀리셨습니다. 사용정지! ]");
		}
	}
	
	/**해당 로직을 참고하여 Index.java - indexView() 에서 SQL 과 연결하여 구현하였다.
	 * @deprecated
	 * @author 권혜진;
	 */
	public void findPw() {
		String user = "권혜진";
		String userid = "hyejin97";
		String pw = "Gpwls1234!";
		String phone = "010-1234-1234";

		int count = 1;
		String result = "";
		
		while(count<=3){
			System.out.print("아이디 입력 : ");
			String inputid = Scan.scanString();
			System.out.print("핸드폰번호 입력 : ");
			String inputPhone = Scan.scanString();

			if (!inputid.equals(userid)) {
				System.out.println("아이디가 틀렸습니다. 아이디먼저 찾고오세요.");
				break;
			}
				
			if (inputPhone.equals(phone)) {
				result = pw;
				System.out.println("[ " + user + " 님 환영합니다. ]");
				System.out.println("당신의 비밀번호는 " + result + " 입니다.");

				break;
			} else {
				System.out.println("[ 핸드폰 번호가 틀려요! ]");
				count++;
			}
		}
		if (count == 4) {
			System.out.println("[ 3회 모두 틀리셨습니다. 사용정지! ]");
		}
	}
	
	/**@author 고결;
	 * @return UserDto;
	 */
	public UserDto login() {
        String id = "";
        String pw = "";
        System.out.print("아이디를 입력해주세요 : ");
        while(true) {
	        id = Scan.scanStringLine();
	        System.out.print("비밀번호를 입력 해주세요 : ");
	        while(true) {
	            pw = "";
	            pw = Scan.scanStringLine();
	            if(pw.matches("^([(A-Z){1,}(a-z0-9){1,}(\\W){1,}]){8,10}")) {
	                return new UserDto(id,pw);
	
	            }else {
	                System.out.print("비밀번호를 다시 입력해주세요 : ");
	            }
	        }
        }
    }

	/**@author 고결;
	 * @return UserDto;
	 */
	public UserDto join() {
        String e_mail = "";
        String nick = "";
        String phone = "";
        System.out.println("------ 회원가입 페이지 입니다. ------");
        System.out.print("아이디 : ");
        String userid = Scan.scanStringLine();
        System.out.print("비밀번호 : ");
        String userpw = "";
        while (true) {
            userpw = "";
            userpw = Scan.scanStringLine();
            if (userpw.matches("^([(A-Z){1,}(a-z0-9){1,}(\\W){1,}]){8,10}")) {
                System.out.print("주소 : ");
                e_mail = Scan.scanStringLine();
                System.out.print("닉네임 : ");
                nick = Scan.scanStringLine();
                System.out.print("휴대폰 번호 : ");
                while (true) {
                    phone = Scan.scanString();
                    if (phone.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")) {
                        System.out.println("------ 회원가입을 축하 드립니다! ------");
                        break;
                    } else {
                        System.out.println("휴대폰 번호를 양식에 맞게 다시 입력해주세요 ");
                        System.out.print("(000-0000-0000) : ");
                    }
                }
                break;
            } else {
                System.out.println("비밀번호를 다시 입력해주세요  : ");
            }
        }
        return new UserDto(userid, e_mail, userpw, nick, phone);
    }

}