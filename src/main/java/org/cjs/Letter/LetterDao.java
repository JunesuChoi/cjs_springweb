package org.cjs.Letter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LetterDao {

	static final String ADD_Letter = "insert letter(title,content,senderId,senderName,receiverId,receiverName) values(?,?,?,?,?,?)";
	
	static final String Reciver_List = "select letterId,title,senderId,senderName,left(cdate,19) cdate from letter where receiverId=? order by letterId desc limit ?,?";
	static final String COUNT_Reciver_List= "select count(letterId) from letter where receiverId=?";
	
	static final String Sender_List = "select letterId,title,receiverId,receiverName,left(cdate,19) cdate from letter where senderId=? order by letterId desc limit ?,?";
	static final String COUNT_Sender_List = "select count(letterId) from letter where senderId=?";
	
	static final String View_Letter = "select letterId,title,content,senderId,senderName,receiverId,receiverName,cdate from letter where letterId=?";
	
	static final String Delete_Letter = "delete from letter where letterId =? and (senderId = ? or receiverId =? )";
	
	
	
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Letter> letterRowMapper = new BeanPropertyRowMapper<>(
			Letter.class);
	
	/**
	 * 글등록
	 */
	public int addLetter(Letter letter) {
		return jdbcTemplate.update(ADD_Letter, letter.getTitle(),
				letter.getContent(), letter.getSenderId(),
				letter.getSenderName(), letter.getReceiverId(),
				letter.getReceiverName());
	}
	/**
	 * 보낸 목록
	 */
	public List<Letter> listLettersOfSender(String senderId,int offset,
			int count) {
		return jdbcTemplate.query(Sender_List, letterRowMapper,
				senderId, offset, count);
	}

	/**
	 * 보낸 편지 갯수
	 */
	public int countLettersSent(String senderId) {
		return jdbcTemplate.queryForObject(COUNT_Reciver_List, Integer.class,
				senderId);
	}
	/**
	 * 받은 목록
	 */
	public List<Letter> listLettersOfReceiver(String receiverId,int offset,
			int count) {
		return jdbcTemplate.query(Reciver_List, letterRowMapper,
				receiverId, offset, count);
	}
	/**
	 * 받은 편지 갯수
	 */
	public int countLettersReceived(String receiverId) {
		return jdbcTemplate.queryForObject(COUNT_Sender_List,
				Integer.class, receiverId);
	}
	
	/**
	 * 조회
	 */
	public Letter getLetter(String letterId) {
		return jdbcTemplate.queryForObject(View_Letter, letterRowMapper,
				letterId);
	}
	/**
	 * 삭제
	 */
	public int deleteLetter(String letterId, String memberId) {
		return jdbcTemplate.update(Delete_Letter, letterId, memberId, memberId);
	}
}
