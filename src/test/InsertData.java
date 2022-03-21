package test;

import java.util.Random;

import dao.PostDao;
import dao.UserDao;
import dto.PostDto;
import dto.UserDto;

/**테스트 데이터 구축용 클래스
 * @author 이민석
 */
public class InsertData {
	static InsertData insertData=new InsertData();
	
	public static void main(String[] args) {
		insertData.createTestUsers();
		insertData.createTestPosts();
	}
	public void createTestPosts() {
		PostDao postDao=new PostDao();
		PostDto postDto=null;
		
		Random random=new Random();
		
		int count=1;
		int ranNumber=random.nextInt(1000);
		while(count<100) {
			postDto=new PostDto(
				("임시 텍스트 내용"+ranNumber).hashCode()*-1+""
			);
			
			String hashtags[]= { "#안녕","#반가워","#사랑해" };
			ranNumber=0;
			ranNumber=random.nextInt(2)+1;
			postDto.setPostTags(hashtags[ranNumber]);
			
			boolean success=postDao.createPost(postDto,"1");
			System.out.println(postDto+(success ? "생성 성공" : "생성 실패"));
			
			count++;
		}
	}
	public void createTestUsers() {
		UserDao userDao=new UserDao();
		UserDto userDto=null;
		
		int count=1;
		Loop1 : while(count<100) {
			String phone="010-0000-";
			switch((count+"").length()) {
			case 1:
				phone+="000"+count;
				break;
			case 2:
				phone+="00"+count;
				break;
			case 3:
				phone+="0"+count;
				break;
			default:
				break Loop1;
			}
			
			userDto=new UserDto(
				"testaccount"+count,
				"testaccount"+count+"@gmail.com",
				"testpw12@",
				"testnickname"+count,
				phone
			);
			
			boolean success=userDao.join(userDto);
			System.out.println(userDto+(success ? "생성 성공" : "생성 실패"));

			count++;
		}
	}
}
