# Java-spring-server

1. openjdk version "17.0.10"
2. Spring CLI v3.2.2
3. Gradle 8.6
4. h2 임베디드 db

# 어플리케이션 실행 명령어

`gradle wrapper`

`gradlew clean build`

`java -jar build/libs/finance-0.0.1-SNAPSHOT.jar`

# 기능
 - gradle을 이용한 라이브러리 관리
 - 회원가입 & 로그인 기능까지 완료

# 추후 추가사항
 - API reqeust를 위한 DTO 분리
 - 로그인시, jwt 토큰 반환
 - 유저관련 api 요청 추가시, 헤더의 토큰 인증 추가
