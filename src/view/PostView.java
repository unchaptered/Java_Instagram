package view;

import dto.PostDto;
import dto.UserDto;
import singleton.Scan;
import singleton.Session;
import validation.Validation;

public class PostView {
    /** @author 권승연;
     */
    public PostDto createBoard() {
        System.out.println("내용을 입력하세요.");
        String content = Scan.scanStringLine();
        PostDto board=new PostDto(content);

        System.out.println("해시태그를 입력하시려면 #을 입력해주세요");
        String choice = Scan.scanString();

        if (choice.equals("#")) {
            System.out.println("해시태그를 입력하세요.");
            String hashtag=Scan.scanStringLine();
            board.setPostTags(hashtag);
        }
        return board;
    }

    /**@author 권승연
     * @param postDetail
     * @return String[0] postText String[1] postTags 또는 null
     */
    public String[] updateBoard(PostDto postDetail) {
		String newText=postDetail.getPostText();
		String newTags=postDetail.getPostTags();
		LoopOfChoice : while(true) {
			System.out.println("1. 내용 수정하기");
			System.out.println("2. 해시태그 수정하기");
			System.out.println("3. 적용하기");
			System.out.println("4. 취소하기");
			int choice = Scan.scanInt();
			switch(choice) {
			case 1:
				System.out.println("내용을 수정해주세요.");
				while(true){
					newText=Scan.scanStringLine();
					if(Validation.checkBlankSpace(newText)) {
						continue; // 45번 줄
					}
					break;
				}
				break;
			case 2:
				System.out.println("해시태그를 입력해주세요.");
				while(true) {
					newTags=Scan.scanStringLine();
					if(Validation.checkBlankSpace(newTags)) {
						continue; // 45번 줄
					}
					newTags=Validation.checkHashtags(newTags);
					break;
				}
				break;
			case 3:
				String result[]=new String[2];
				result[0]=newText;
				result[1]=newTags;
				return result;
			case 4:
				return null;
			default:
				System.out.println("1,2,3,4 중에 입력해주세요.");
				continue LoopOfChoice;
			}
		}
    }

    /** @author 권혜진;
     */
    public String validHashTags(String hashtags) {
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

	
	/** @author 김명현;
	 */
	public String addLikeUser(String likeList) {
		UserDto userDto=(UserDto) Session.get("loggedInUser");
		String userId=userDto.getUserId();
		
		String likeUsers[]=likeList.split(","); 
		String likeUserAfter="";
		
		for(String user : likeUsers) {
			if(!user.equals(userId)) {
				likeUserAfter+=user;
			}
		}
		
		return likeUserAfter;
	}
}
