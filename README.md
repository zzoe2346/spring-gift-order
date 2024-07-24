## 1단계 - 카카오 로그인
- api사용 준비는 강의 시간에 완성
- 로직 계획
    1. 인가 코드 요청
    2. 인가 코드로 카카오 토큰 요청
    3. 카카오 토큰으로 로그인 요청자의 이메일 정보 얻기
    4. 전에 구현해놓은 Member DB에서 해당 이메일 존재하는지 확인
    5. 존재하면 Jwt토큰 발행하여 Json으로 반환
- 외부 api와 하는 테스트방법 고민 및 구현