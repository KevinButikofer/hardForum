package com.hardforum.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hardforum.models.SubForum;
import org.springframework.data.repository.query.Param;
import com.hardforum.models.Topic;


@Repository("topicRepository")
public interface TopicRepository extends JpaRepository<Topic, Integer>{
	
	Topic findById(int topic_id);
	List<Topic> findByNameContaining(String name);
	List<Topic> findBySubForumOrderByUpdateDateTimeDesc(SubForum subForum);
	List<Topic> findFirst10ByOrderByUpdateDateTimeDesc();

	@Query( value = "select COUNT(*) from topic t where t.sub_forum_id=?", nativeQuery = true)
	Integer findPostNumber();
	
    @Query(value="SELECT * FROM topic INNER JOIN user ON user.user_id = topic.user_id INNER JOIN subforum ON subforum.sub_forum_id = topic.sub_forum_id WHERE user.name LIKE %?2% AND subforum.sub_forum_id = ?3 AND topic.name LIKE %?1% ", nativeQuery = true)
    public List<Topic> find(@Param("topicName") String topicName, @Param("authorName") String authorName, @Param("categoryID") int categoryID);
    Page<Topic> findBySubForumOrderByUpdateDateTimeDesc(Pageable pageable, SubForum subForum);
    @Transactional
    Long removeById(int topic_id);
}
