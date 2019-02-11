package com.example.blog.repository.datajpa;

import com.example.blog.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMessageRepository extends JpaRepository<Message, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Message m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Message save(Message message);

    @Override
    Optional<Message> findById(Integer id);

    @Override
    @Query("SELECT m FROM Message m ORDER BY m.dateTime DESC")
    List<Message> findAll();

    @Query("SELECT m FROM Message m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Message> findAll(@Param("userId") int userId);
}
