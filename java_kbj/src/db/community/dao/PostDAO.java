package db.community.dao;

import org.apache.ibatis.annotations.Param;

import db.community.model.vo.CommunityVO;

public interface PostDAO {
	CommunityVO selectCommunity(@Param("community")String community);
	boolean insertCommunity(@Param("community")String community);
}