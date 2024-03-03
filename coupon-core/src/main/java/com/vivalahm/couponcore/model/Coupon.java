package com.vivalahm.couponcore.model;

import com.vivalahm.couponcore.exception.CouponIssueException;
import com.vivalahm.couponcore.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "coupons")
public class Coupon extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CouponType couponType;

    private  Integer totalQuantity;

    @Column(nullable = false)
    private int issuedQuantity;

    @Column(nullable = false)
    private int discountAmount;

    @Column(nullable = false)
    private int minimumAmount;

    @Column(nullable = false)
    private LocalDateTime dateIssueStart;

    @Column(nullable = false)
    private LocalDateTime dateIssueEnd;

    /**
     * 쿠폰 발급 수량 검증
     * @return
     */
    public boolean availableIssueQuantity(){
        if(totalQuantity == null){
            return true; // 발급 제한 수량이 없는 경우
        }
        return totalQuantity > issuedQuantity;
    }

    /**
     * 쿠폰 발급 기간 검증
     * @return
     */
    public boolean availableIssueDate(){
        LocalDateTime now = LocalDateTime.now();//현재 시간 가져와서
        return now.isAfter(dateIssueStart) && now.isBefore(dateIssueEnd);//발급 시작일 이후이고 발급 종료일 이전이면 true
    }

    /**
     * 쿠폰 발급
     * @return
     */
    public void issue(){
        if(!availableIssueQuantity()){
            throw new CouponIssueException(ErrorCode.INVALID_COUPON_ISSUE_QUANTITY, "쿠폰 발급 가능 수량 초과합니다. total: %s, issued: %s".formatted(totalQuantity, issuedQuantity));
        }
        if(!availableIssueDate()){
            throw new CouponIssueException(ErrorCode.INVALID_COUPON_ISSUE_DATE, "쿠폰 발급 기간이 아닙니다. request: %s, issueStart: %s, issueEnd: %s".formatted(LocalDateTime.now(), dateIssueStart, dateIssueEnd));
        }
        //검증이 끝나야만 발급 수량을 증가시킨다.
        issuedQuantity++;
    }
}
