package singleton;

import java.util.InputMismatchException;
import java.util.Scanner;

/** Scan 는 정적 Scanner 를 사용하기 위한 Singleton 클래스입니다.<br><br>
 * 
 * 내부에 private static Scanner 타입 변수 scan이 있으며,<br>
 * 이를 초기화하고 사용하기 위한 정적 메서드들을 가지고 있습니다.
 * 
 * 이 클래스는 Utility(기능성) 클래스로 객체화를 막기 위해,
 * 생성자를 private 으로 만들었고 우회 호출의 경우의 수를 막기 위해,
 * 생성자를 강제 호출 시 에러를 던지게 구현하였습니다.
 * 
 * @author 이민석
 */
public class Scan {
	private static Scanner scan=new Scanner(System.in);
	
	/** @throw new AssertionException();
	 */
	private Scan() {
		throw new AssertionError();
	}
	
	/** scan=new Scanner(System.in);
	 */
	public static void resetScanner() {
		scan=new Scanner(System.in);
	}
	
	/** scan=new Scanner(System.in);<br>
	 * @return scan.nextLine();
	 */
	public static String scanStringLine() {
		resetScanner();
		return scan.nextLine();
	}
	
	/** @return scan.next();
	 */
	public static String scanString() {
		return scan.next();
	}
	
	/** @return scan.nextInt();
	 */
	public static int scanInt() {
		int num=0;
		while(true) {
			num=0;
			scan=new Scanner(System.in);
			try {
				num=scan.nextInt();
				break;
			}catch(InputMismatchException ime) {
				System.out.println("숫자를 입력해야 합니다 : ");
				System.out.println(ime);
				continue; 
			}
		}
		return num;
	}
	
	/**@deprecated LocalDate 나 LocalDateTime 나 OffsetDateTime 등을 쓰자...
	 * @return String Regex Date 타임
	 */
	@Deprecated public static String scanDate() {
		String date;
		while(true) {
			date=null;
			date=scanStringLine();
			
			boolean dateValid=date.matches("(\\d){4,4}\\W{1,1}(\\d){2,2}\\W{1,1}(\\d){2,2}");
			if(dateValid==true) {
				
				String dateForm[]=date.split("\\W");
				// 연도 검사
				int yearValid=Integer.parseInt(dateForm[1]);
				if(yearValid<=0) {
					System.out.println("0 초과 숫자를 입력하세요 : ");
					continue;
				}
				
				// 월 검사
				int monthValid=Integer.parseInt(dateForm[1]);
				if(monthValid<=0 || monthValid>12) {
					System.out.println("0 초과 12 이하을 입력하세요 : ");
					continue;
				}
				
				// 일 검사
				int dayValid=Integer.parseInt(dateForm[2]);
				// 1  2      3  4  5  6  7  8  9  10  11  12
				// 31 28(29) 31 30 31 30 31 31 30 31 30 31
				int maxDay=0;
				switch(monthValid) {
				case 1: case 3: case 5: case 7: case 8: case 10: case 12:
					maxDay=31;
					break;
				case 2:
					maxDay=(yearValid %4 == 0 && yearValid %100 !=0 || yearValid %400 ==0) ? 29 : 28;
					break;
				case 4: case 6: case 9: case 11:
					maxDay=30;
					break;
				}
				if(dayValid <=0 || dayValid>maxDay) {
					System.out.printf("0 초과 %d 이하를 입력하세요 : ", maxDay);
					continue;
				}
				break;
			}else {
				System.out.println("YYYY-MM-DD 에 맞춰주세요 : ");
				continue;
			}
		}
		
		return date;
	}
}