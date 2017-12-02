package com.plan2.example_rest_api.ParkSDK.Network.Volley;

/**
 * Created by park on 2017-01-14.
 */

public class NetworkCodeValue {
    public static final String SUCCESS = "1000";                                            // 성공
    public static final String FAIL = "1001";                                               // 실패
    public static final String ID_IS_OVERLAP = "2001";                                      // 아이디 중복
    public static final String CELL_PHONE_NUMBER_IS_OVERLAP = "2002";                       // 핸드폰 번호 중복
    public static final String ID_IS_NULL = "4001";                                         // 아이디 없음
    public static final String PW_IS_DIFF = "4002";                                         // 패스워드 틀림
    public static final String CELL_PHONE_NUBER_IS_DIFF = "4003";                           // 핸드폰 번호 틀림
    public static final String EMAIL_SEND_FAIL = "4004";                                    // 이메일 전송 실패
    public static final String SERVICE_RATING_RE_PARTICIPATION = "6001";                    // 해당 샵의 서비스 평점에 이미 참여 했을 때
    public static final String SHOP_COUPON_NULL = "7001";                                   // 해당 샵에 교환 등록된 쿠폰이 없을 때
    public static final String ID_AREA_AD_NULL = "8001";                                    // 해당 아이디의 지역에 광고가 없을 때
}
