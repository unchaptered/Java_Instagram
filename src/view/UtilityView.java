package view;

import java.util.ArrayList;
import java.util.LinkedList;

import dao.PostDao;
import dto.PostDto;
import dto.UserDto;
import singleton.Scan;
import singleton.Session;
import validation.Validation;

public class UtilityView {
	/**@author 이민석
	 * @return String value;
	 */
	public String getSearchValue() {
		String value=Scan.scanStringLine();
		return value;
	}
	
	/**@author 이민석
	 * @return
	 */
	public String getIdValue() {
		String value=Scan.scanStringLine();
		return value;
	}
	
	/**@author 이민석
	 * @return
	 */
	public String getPhoneValue() {
		String value=null;
		while(true){
			value=Scan.scanStringLine();
			if(Validation.checkPhoneRegex(value)) {
				break;
			}else {
				System.out.println("01X-XXXX-XXX 에 맞춰서 입력해주세요");
				continue;
			}
		}
		return value;
	}
	
	/**UtilityDao 에서는 MySQL 에서 최대 100개의 데이터를 받아와 이를 ArrayList<PostDto\\> 에 넣는다.
	 * ArrayList 는 처리 및 삭제 속도가 느리기에 LinkedList 타입 변수를 선언하고 옮겨 담아준다.
	 * @author 이민석
	 * @param ArrayList < PostDto > postList
	 */
	public void printPosts(ArrayList<PostDto> postList) {
		final int SIZE=postList.size(); 
		final int MAX_PAGE=(SIZE)/10;
		int page=0;
		int count=0;
		
		int choice=0;
		LoopOfPages : while(true) {
			LoopOfFiles : while(true) {
				int index=page*10 + count;
				if(index >= SIZE) {
					System.out.println("마지막 페이지 입니다.");
					break LoopOfFiles;
				}
				if(count>=9) {
					count=0;
					break LoopOfFiles;
				}
				System.out.println(index+" 번"+postList.get(page*10+count));
				count++;
			}
			
			System.out.println("1. 다음 페이지 보기");
			System.out.println("2. 이전 페이지 보기");
			System.out.println("3. 자세히 보기");
			System.out.println("4. 나가기");
			choice=Scan.scanInt();
			switch(choice) {
			case 1:
				if(page==MAX_PAGE) {
					System.out.println("마지막 페이지 입니다.");
					continue LoopOfPages;
				}
				page++;
				System.out.println(page+"페이지 결과");
				continue LoopOfPages;
			case 2:
				if(page==0) {
					System.out.println("첫 번째 페이지 입니다.");
					continue LoopOfPages;
				}
				page--;
				System.out.println(page+"페이지 결과");
				continue LoopOfPages;
			case 3:
				System.out.print("게시글 선택 : ");
				int choiceIndex=Scan.scanInt();
				if(choiceIndex > SIZE) {
					System.out.print(choiceIndex+"번은 없는 게시글 번호입니다.");
					continue LoopOfPages;
				}
				PostDto postDetail=postList.get(choiceIndex);
				System.out.println(choiceIndex+" 번"+postDetail);
				
				UserDto userSession=(UserDto) Session.get("loggedInUser");
				LoopOfFunctions : while(true) {
					System.out.println("1. 좋아요 누르기");
					System.out.println("2. 정보 수정하기");
					System.out.println("3. 나가기");
					int choiceFunction;
					choiceFunction=Scan.scanInt();
					// 좋아요 메서드 (명현님)
					switch(choiceFunction) {
					case 1:
						// 메서드 호출
						// Dao 메서드 호출
						continue LoopOfFunctions;
					case 2:
						PostView postView=new PostView();
						PostDao postDao=new PostDao();
						if(!postDetail.getPostOwner().equals(userSession.getUserCode())) {
							System.out.println("내 게시글이 아니여서 수정할 수 없습니다.");
							break;
						}
						String postUpdated[]=postView.updateBoard(postDetail);
						
						if(postUpdated==null) {
							continue LoopOfFunctions;
						}
						
						boolean compareToText=postDetail.getPostText().equals(postUpdated[0]);
						boolean compareToTags=postDetail.getPostTags().equals(postUpdated[1]);
						
						if(compareToText) {
							if(compareToTags) {
								// 수정안한거
								continue LoopOfFunctions;
							}else {
								// Tags 수정한거
								System.out.println("태그만 변경");
								// userCode 와 postOwner 비교
								postDao.updatePostTags(postUpdated[1], userSession.getUserCode(), postDetail.getPostCode());
								postDetail.setPostTags(postUpdated[1]);
								postList.set(choiceIndex, postDetail);
								page=0;
								count=0;
								break;
							}
						}else{
							if(compareToTags) {
								// Text 수정한거
								System.out.println("내용만 변경");
								// userCode 와 postOwner 비교
								postDao.updatePostText(postUpdated[0], userSession.getUserCode(), postDetail.getPostCode());
								postDetail.setPostText(postUpdated[0]);
								postList.set(choiceIndex, postDetail);
								page=0;
								count=0;
								break;
							}else {
								// Text, Tags 수정한거
								System.out.println("둘다 변경");
								// userCode 와 postOwner 비교
								postDao.updatePostAll(postUpdated, userSession.getUserCode(), postDetail.getPostCode());
								postDetail.setPostTags(postUpdated[1]);
								postDetail.setPostText(postUpdated[0]);
								postList.set(choiceIndex, postDetail);
								page=0;
								count=0;
								break;
							}
						}
					case 3:
						System.out.println("게시글 보기를 종료합니다.");
						break LoopOfFunctions;
					default:
						System.out.println("1,2,3 중에 입력해주세요.");
						continue LoopOfFunctions;
					}
				}
				continue LoopOfPages;
			case 4:
				System.out.println("게시글 목록 보기를 종료합니다.");
				break LoopOfPages;
			default:
				System.out.println("1,2,3,4 중에 입력해주세요.");
				continue LoopOfPages;
			}
		}
	}

	public void printDetailPosts(PostDto postList) {
	}
	
	/**@author 이민석
	 * @param ArrayList < UserDto > userList
	 */
	public void printUser(ArrayList<UserDto> userList) {
		LinkedList<UserDto> postLink=new LinkedList<>(userList);
		
		final int SIZE=postLink.size();
		final int MAX_PAGE=(SIZE)/10;
		int page=0;
		
		int count=0;
		int choice=0;
		Loop1 : while(true) {
			
			System.out.println((page+1)+"페이지 결과");
			Loop2 : while(true) {
				int index=page*10 + count;
				if(index > SIZE) {
					System.out.println("마지막 페이지 입니다.");
					break Loop2;
				}
				if(count>=9) {
					count=0;
					break Loop2;
				}
				System.out.println(index+" 번"+postLink.get(page*10+count));
				count++;
			}
			
			System.out.println("1. 다음 페이지 보기");
			System.out.println("2. 이전 페이지 보기");
			System.out.println("3. 나가기");
			choice=Scan.scanInt();
			switch(choice) {
			case 1:
				if(page==MAX_PAGE) {
					System.out.println("마지막 페이지 입니다.");
					continue Loop1;
				}
				page++;
				continue Loop1;
			case 2:
				if(page==0) {
					System.out.println("첫 번째 페이지 입니다.");
					continue Loop1;
				}
				page--;
				continue Loop1;
//				@Deprecated 작업 우선순위에서 밀린 관계로 로직을 주석 처리해두었다(2021-12-20 이민석)
//			case 3:
//				System.out.print("사용자 번호 : ");
//				int choiceIndex=Scan.scanInt();
//				if(choiceIndex > SIZE) {
//					System.out.print(choiceIndex+"번은 없는 게시글 번호입니다.");
//					continue Loop1;
//				}
//				UserDto postDetail=userList.get(choiceIndex);
//				System.out.println(postDetail);
//				String postCode=postDetail.getUserCode();
//				// 상세보기 페이지
//				continue Loop1;
			case 3:
				System.out.println("게시글 보기를 종료합니다.");
				break Loop1;
			default:
				System.out.println("1,2,3 중에 입력해주세요.");
				continue Loop1;
			}
		}
	}

	public void printDetialPosts(UserDto userDetail) {
	}
}