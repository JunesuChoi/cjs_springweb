package org.cjs.Letter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class LetterDao {
	
	static final String ADD_Letter = "insert letter(title, content, senderId, "
			+ "senderName, receiverId, receiverName) values('?', '?',?,'?',?,'?')";
	
	static final String Reciver_List = "select letterId,title,senderId,senderName,cdate from letter where receiverId=?";
	
	static final String Sender_List = "select letterId,title,receiverId,receiverName,cdate from letter where senderId=?";
	
	static final String View_Letter = "letterId,title,content,senderId,senderName,receiverId,receiverName,cdate "
			+ "from letter where letterId=? and (senderId= ? or receiverId=?)";
	
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
	public List<Letter> listLettersOfSender(String senderId) {
		return jdbcTemplate.query(Sender_List, letterRowMapper,
				senderId);
	}
	
	/**
	 * 받은 목록
	 */
	public List<Letter> listLettersOfReceiver(String receiverId) {
		return jdbcTemplate.query(Reciver_List, letterRowMapper,
				receiverId);
	}
	/**
	 * 조회
	 */
	public Letter getLetter(String letterId, String memberId) {
		return jdbcTemplate.queryForObject(View_Letter, letterRowMapper,
				letterId, memberId, memberId);
	}
	/**
	 * 삭제
	 */
	public int deleteLetter(String letterId, String memberId) {
		return jdbcTemplate.update(Delete_Letter, letterId, memberId, memberId);
	}
}
