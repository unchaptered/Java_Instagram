package view;

import java.util.ArrayList;

import dao.PostDao;
import dao.UserDao;
import dao.SearchDao;
import dto.PostDto;
import dto.UserDto;
import singleton.Scan;
import singleton.Session;
import validation.Validation;

public class Index {
	public static Index index=new Index();
	
	public static void main(String[] args) {
		System.out.println("�ν�Ÿ�׷�");
		
		while(true) {
			UserDto userSession=(UserDto) Session.get("loggedInUser");
			
			if(userSession==null) {
				System.out.println("���Ǿ���");
				index.indexView();
			}else{
				System.out.println("��������");
				index.mainView(userSession);
			}
		}
	}
	
	public void indexView() {
		int choice=0;
		
		UserDao userDao=null;
		UserView userView=null;
		UtilityView utilityView=null;
		
		boolean flag=false;
		Loop1 : while(flag==false) {
			System.out.println("1. �α���");
			System.out.println("2. ȸ������");
			System.out.println("3. ���̵� ã��");
			System.out.println("4. ��й�ȣ ã��");
			System.out.println("5. ������");
			
			choice=0;
			choice=Scan.scanInt();
			switch(choice) {
			case 1:
                System.out.println("�α����� �����մϴ�.");
                userView= new UserView();
                userDao= new UserDao();
                
                UserDto user = userView.login();
                UserDto userDB=userDao.login(user);
                
                Session.set("loggedInUser", userDB);
                
                flag=true;
                break;
			case 2:
                System.out.println("ȸ�������� �����մϴ�.");
                userView = new UserView();
                userDao=new UserDao();
                
                UserDto userViewDto = userView.join();
                System.out.println(userDao.join(userViewDto));
                
                flag=true;
                break;
			case 3:
				System.out.println("���̵� ã�⸦ �����մϴ�.");
				utilityView=new UtilityView();
				userDao=new UserDao();
				
				String userPhone=utilityView.getPhoneValue();
				
				UserDto userDto=userDao.findUserDtoByPhone(userPhone);
				if(userDto==null) {
					System.out.println("�������� �ʴ� ���̵��Դϴ�.");
					break;
				}
				System.out.println("����� ���̵� : "+userDto.getUserId());
				break;
			case 4:
				// ��� �׽�Ʈ ���� �� DB �������
				System.out.println("��й�ȣ ã�⸦ �����մϴ�.");
				utilityView=new UtilityView();
				userDao=new UserDao();
				
				String userIdd=null;
				while(true) {
					System.out.print("���̵� : ");
					userIdd=utilityView.getIdValue();
					if(Validation.checkBlankSpace(userIdd)) {
						System.out.println("������ �˻��� �� �����ϴ�.");
						continue;
					}
					if(!Validation.checkIdLength(userIdd)) {
						System.out.println("8�ڸ� �̻����� �Է��ؾ� �մϴ�,");
						continue;
					}
					break;
				}
				UserDto userDtoo=userDao.findUserDtoById(userIdd);
				if(userDtoo==null) {
					System.out.println("�������� �ʴ� ���̵��Դϴ�.");
					break;
				}
				while(true) {
					System.out.print("�ڵ��� ��ȣ :");
					String userPhonee=utilityView.getPhoneValue();
					if(!userPhonee.equals(userDtoo.getUserPhone())) {
						System.out.println("�ڵ��� ��ȣ�� ��ġ���� �ʽ��ϴ�.");
					}else {
						System.out.println("����� ��й�ȣ :  "+userDtoo.getUserPw());
					}
					break;
				}
				
				break;
			case 5:
				System.out.println("���α׷��� �����մϴ�.");
				break Loop1;
			default:
				System.out.println("1,2,3 �߿� �Է����ּ���.");
				continue Loop1;
			}
		}
	}
	
	public void mainView(UserDto userSession) {
        System.out.println("���� ������");

        int choice=0;
        
        UserDao userDao=null;
        PostView postView=null;
        PostDao postDao=null;
        UtilityView utilityView=null;
        SearchDao utilityDao=null;
        
        Loop1 : while(true) {
        	System.out.println("1. �Խñ� �ۼ�");
        	System.out.println("2. �� �Խñ�");
        	System.out.println("3. ���� �˻� ");
        	System.out.println("4. �Խñ� �˻�(���� �Է�)");
        	System.out.println("5. �Խñ� �˻�(�ؽ��±� �Է�)");
            System.out.println("6. �α׾ƿ� �ϱ�");
            System.out.println("7. Ż���ϱ�");
            
            choice=0;
            choice=Scan.scanInt();
            switch(choice) {
            case 1:
                System.out.println("�Խñ� �ۼ� ����� ����Ǿ����ϴ�.");
                postView=new PostView();
                postDao= new PostDao();

                // Dto
                PostDto postDto=postView.createBoard();
                String hashtags=postDto.getHashtag();
                String hashtagsAfter=postView.validHashTags(hashtags);
                postDto.setPostTags(hashtagsAfter);
                // Dao
                postDao.createPost(postDto, userSession.getUserCode());
                break;            	
            case 2:
            	System.out.println("�� �Խñ� ���⸦ �����ϼ̽��ϴ�.");
            	utilityView=new UtilityView();
              	utilityDao=new SearchDao();

            	ArrayList<PostDto> postListByMine=utilityDao.searchPostByOwnerCode(userSession.getUserCode());
            	utilityView.printPosts(postListByMine);
            	break;
            case 3:
            	System.out.println("���� �˻��� �����Ͽ����ϴ�.");
            	System.out.print("�˻�� �Է����ּ��� : ");
            	utilityView=new UtilityView();
            	String searchUserValue=utilityView.getSearchValue();
            	if(Validation.checkBlankSpace(searchUserValue)) {
            		System.out.println("������ �˻��� �Ұ����մϴ�.");
            		break;
            	}
            	utilityDao=new SearchDao();
            	
            	ArrayList<UserDto> userList=utilityDao.searchUserById(searchUserValue);
            	utilityView.printUser(userList);
            	break;
            case 4:
            	System.out.println("�Խñ� �˻��� �����ϼ̽��ϴ�.");
            	System.out.print("�˻�� �Է����ּ��� : ");
            	utilityView=new UtilityView();
            	String searchPostByValue=utilityView.getSearchValue();
            	if(Validation.checkBlankSpace(searchPostByValue)) {
            		System.out.println("������ �˻��� �Ұ����մϴ�.");
            		break;
            	}
            	utilityDao=new SearchDao();
            	
            	ArrayList<PostDto> postListByText=utilityDao.searchPostByTexts(searchPostByValue);
            	utilityView.printPosts(postListByText);
            	break;
            case 5:
            	System.out.println("�ؽ� �±� �˻��� �����Ͽ����ϴ�.");
            	System.out.print("�˻�� �Է����ּ��� : ");
            	utilityView=new UtilityView();
            	postView=new PostView();
            	String searchPostByHashtag=utilityView.getSearchValue();
            	if(Validation.checkBlankSpace(searchPostByHashtag)) {
            		System.out.println("������ �˻��� �Ұ����մϴ�.");
            		break;
            	}
            	String searchPostByHashtagAfter=postView.validHashTags(searchPostByHashtag);
            	utilityDao=new SearchDao();
            	
            	ArrayList<PostDto> postListByHashtag=utilityDao.searchPostByHashtags(searchPostByHashtagAfter);
            	utilityView.printPosts(postListByHashtag);
            	break;
            case 6:
                System.out.println("�α׾ƿ��� �����Ͽ����ϴ�.");
                Session.set("loggedInUser", null);
                break Loop1;
            case 7:
            	System.out.println("Ż���Ͻðڽ��ϱ�?");
            	userDao=new UserDao();
            	String userId=userSession.getUserId();
            	String userPw=null;
            	
            	while(true) {
            		System.out.println("��й�ȣ : ");
            		userPw=Scan.scanStringLine();
            		if(Validation.checkBlankSpace(userPw)) {
            			System.out.println("������ �Է��� �Ұ����մϴ�.");
            			continue;
            		}
            		break;
            	}
            	
            	boolean success=userDao.deleteUser(userId, userPw);
            	if(success) {
            		System.out.println("Ż�� �����Ͽ����ϴ�.");
            	}else {
            		System.out.println("Ż�� �����Ͽ����ϴ�.");
            		System.out.println("��α��� ���ּ���.");
            	}
            	Session.set("loggedInUser", null);
            	break Loop1;
            default:
                System.out.println("1,2,3,4,5,6 �߿� �Է����ּ���.");
                continue Loop1;
            }
        }
    }
}