package rule.domain;

public class UserDO {

	long id;
	long tag;
	String name;

	public UserDO(long aId, String aName, long aUserTag) {
		this.id = aId;
		this.tag = aUserTag;
		this.name = aName;
	}

	public String getName() {
		return name;
	}

	public long getUserId() {
		return id;
	}

	public long getUserTag() {
		return tag;
	}
}
