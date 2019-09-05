package com.imooc.homepage.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "homepage_user_course")
public class HomepageUserCourse {

    /**
     * 数据表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** 用户id */
    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 课程id */
    @Basic
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    /** 创建时间 */
    @Basic
    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /** 修改时间 */
    @Basic
    @LastModifiedDate
    @Column(name = "modified_time", nullable = false)
    private Date modifiedTime;
}
