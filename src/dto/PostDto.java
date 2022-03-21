package dto;

import java.time.LocalDateTime;

/**@author 권승연
 */
public class PostDto {
	
	// 기본키 필드
	private String postCode = null;
	private String postOwner = null;
	
    // 필수값 필드
    private String postText;
    private String postTime;

    // 선택값 필드
    private String postTags;
    private String postLikeList;

    /**@author 권승연;
     */
    public PostDto(String postText) {
        this.postText = postText;
        this.postTime = LocalDateTime.now().toString().substring(0, 10);
    }

	/**@author 권승연;
     */
    @Override public String toString() {
    	if(postCode==null) {
            if(postTags==null) {
                return postText + "\t" + postTime + "\t" + postOwner;
            }else {
                return postText + "\t" + postTags +"\t" + postTime + "\t" + postOwner;
            }
    	}else {
            if(postTags==null) {
                return postCode + "\t" + postText + "\t" + postTime + "\t" + postOwner;
            }else {
                return postCode + "\t" + postText + "\t" + postTags +"\t" + postTime + "\t" + postOwner;
            }
    	}
    }

    /**@author 권승연
     */
    @Override public boolean equals(Object obj) {
        boolean check = false;

        if (!(obj instanceof PostDto)) {
            return check;
        }
        PostDto postDto=(PostDto) obj;
        if(postCode!=null) {
            if(postCode.equals(postDto.getPostCode())) check = true;
        }

        if(!postText.equals(postDto.getPostText())) return check=false;
        if(!postTime.equals(postDto.getPostTime())) return check=false;

        return check=true;
    }

    /** @author 권승연
     */
    @Override public int hashCode() {
        final int hash = 31;
        int result = 1;

        result = (result * hash) + (postCode == null ? 0 : postCode.hashCode());
        result = (result * hash) + postText.hashCode();
        result = (result * hash) + postTime.hashCode();

        return result;
    }
    
    // Getters
    
    public String getPostCode() {
		return postCode;
	}
	public String getPostText() {
        return postText;
    }
    public String getPostTime() {
        return postTime;
    }
    public String getPostTags() {
        return postTags;
    }
    public String getHashtag() {
        return postTags;
    }
    public String getPostLikeList() {
		return postLikeList;
	}
	public String getPostOwner() {
		return postOwner;
	}
	
	// Setters

	public void setPostTags(String postTags) {
        this.postTags = postTags;
    }
    public void setPostText(String postText) {
		this.postText = postText;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public void setPostLikeList(String postLikeList) {
		this.postLikeList = postLikeList;
	}
	public void setPostOwner(String postOwner) {
		this.postOwner = postOwner;
	}
}