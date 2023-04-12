package com.uplus.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * net.cqnews.reporting.command.core.reportingform.domain.model
 * 附件表
 *
 * @Author: JingSongChan
 * @Date: 2021-3-4
 * @Time: 14:22
 */
@Entity
@Table(name = "appendix")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Appendix implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) COMMENT '主键Id'")
    private Long id;

    @CreatedBy
    @Column(columnDefinition = "bigint(20) COMMENT '创建人Id'")
    private Long creatorId;

    @CreatedDate
    @Column(columnDefinition = "datetime COMMENT '创建时间'")
    private LocalDateTime createTime;

    @LastModifiedBy
    @Column(columnDefinition = "bigint(20) COMMENT '修改人Id'")
    private Long updateId;

    @LastModifiedDate
    @Column(columnDefinition = "datetime COMMENT '修改时间'")
    private LocalDateTime updateTime;

    @Column(columnDefinition = "tinyint(1) NOT NULL COMMENT '是否删除'")
    private Byte deleteStatus;

    @Column(columnDefinition = "bigint(20) COMMENT '关联Id'")
    private Long associateId;

    @Column(columnDefinition = "varchar(255)  COMMENT '原始文件名称'")
    private String originalFileName;

    @Column(columnDefinition = "varchar(255)  COMMENT '文件后缀'")
    private String fileExt;

    @Column(columnDefinition = "varchar(255)  COMMENT '服务器文件名称'")
    private String fileName;

    @Column(columnDefinition = "varchar(255)  COMMENT 'bucketName'")
    private String bucketName;

    @Column(columnDefinition = "varchar(255)  COMMENT '文件访问地址'")
    private String fileUrl;

}
