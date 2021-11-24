package com.instagramgaul.demo.repository;

import com.instagramgaul.demo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Query("SELECT X FROM User X WHERE X.email=?1 AND X.deletedAt = null")
    public User findByEmail(String email);

    @Transactional
    @Query("SELECT X FROM User X WHERE X.username=?1 AND X.deletedAt = null")
    public User findByUsername(String username);

    @Transactional
    @Query("SELECT X FROM User X WHERE X.id=?1 AND X.deletedAt = null")
    public User findById(int id);

    @Transactional
    @Query("SELECT X FROM User X WHERE X.deletedAt = null")
    public List<User> getAllUsers();

    @Transactional
    @Query("UPDATE User X SET X.deletedAt =?#{T(java.sql.Timestamp).valueOf(T(java.time.LocalDateTime).now())} WHERE X.id=?1")
    public User deleteById(int id);
}