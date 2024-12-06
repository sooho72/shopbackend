package com.pgm.shopserver.security.jwt;

import com.pgm.shopserver.security.UserPrinciple;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
// JWT 란?
// 웹에서 사용되는 JSON 형식의 토큰에 대한 표준 규격.
// 주로 사용자의 인증(authentication) 또는 인가(authorization) 정보를 서버와 클라이언트 간에 안전하게 주고 받기
//위해서 사용
// JWT 토큰 웹에서 보통 Authorization HTTP 헤더를 Bearer <토큰>으로 설정하여 클라이언트에서 서버로 전송
// 서버에서는 토큰에 포함되어 있는 서명(signature) 정보를 통해서 위변조 여부를 빠르게 검증할 수 있음
// JWT 의 주요한 이점은 사용자 인증에 필요한 모든 정보는 토큰 자체에 포함하기 때문에 별도의 인증 저장소가
//필요 없음
public interface JwtProvider {
    String generateToken(UserPrinciple auth);
//
//    설명:
//
//    이 메서드는 사용자의 인증 정보를 기반으로 JWT(JSON Web Token) 또는 다른 형태의 토큰을 생성하는 역할을 합니다.
//    입력으로 UserPrinciple 객체를 받는데, 이는 인증된 사용자의 세부 정보를 담고 있을 가능성이 높습니다(예: 사용자 ID, 권한, 이름 등).
//    어디에 사용?:
//
//    주로 로그인 성공 시 호출되어 클라이언트에게 토큰을 발급합니다.
//    발급된 토큰은 이후 요청에서 사용자의 인증 상태를 유지하는 데 사용됩니다.

    Authentication getAuthentication(HttpServletRequest request);
//    설명:
//
//    이 메서드는 HTTP 요청(HttpServletRequest)에서 인증 정보를 추출하여 Authentication 객체를 반환합니다.
//    보통 HTTP 헤더(예:Authorization)에 포함된 토큰을 확인하고 이를 기반으로 인증된 사용자 정보를 생성합니다.
//    어디에 사용?:
//
//    클라이언트로부터 받은 요청이 유효한지 확인하고, 해당 요청을 수행할 권한이 있는 사용자인지 판별하는 데 사용됩니다.
//            예: API 요청 처리 전에 사용자 인증 및 권한 검사를 수행.
    boolean isTokenValid(HttpServletRequest request);
//    설명:
//
//    이 메서드는 요청에서 토큰을 추출한 뒤, 해당 토큰이 유효한지 확인합니다.
//    토큰의 유효성을 검증하는 데 사용될 수 있는 요소:
//    토큰이 만료되었는지 확인.
//    서명이 올바른지 확인.
//    토큰의 발행자 및 사용자 정보가 신뢰할 수 있는지 확인.
//    어디에 사용?:
//
//    인증이 필요한 요청에서, 요청을 처리하기 전에 토큰이 유효한지 확인하여 불법 접근을 방지.

}
