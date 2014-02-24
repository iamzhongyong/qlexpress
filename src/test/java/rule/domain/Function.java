package rule.domain;

public class Function {
	/**
     * 判断一个用户TAG的第X位是否为1。这个的demo,其实现合理性不考虑
     * @param user
     * @param tagBitIndex
     * @return
     */
    public boolean userTagJudge(UserDO user,int tagBitIndex){
    	boolean r =  (user.getUserTag() & ((long)Math.pow(2, tagBitIndex))) > 0;
    	return r;
    }
	
	/**
	 * 判断一个用户是否订购过某个商品
	 * @param user
	 * @param goodsId
	 * @return
	 */
	public boolean hasOrderGoods(UserDO user,long goodsId){
		//随机模拟一个
		if(user.getUserId() % 2 == 1){
			return true;
		}else{
			return false;
		}
	}
}
