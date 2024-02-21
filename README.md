# coupon-management-system
선착순 쿠폰 발급 시스템

### 선착순 쿠폰 발급 이벤트?

- 한정된 수량의 쿠폰을 먼저 신청한 사용자에게 제공하는 이벤트

### 요구 사항

- `이벤트 기간 내`에(예시 2024-02-01일 오후 1시~ 2024-02-10일 오후 1시) 발급
- 선착순 이벤트는 `유저당 1번`의 쿠폰 발급
- 선착순 쿠폰의 `최대 쿠폰 발급 수량` 설정

### 쿠폰 발급 기능

- 쿠폰 발급 기능
  - 쿠폰 발급 기간 검증 (기간 내 발급)
  - 쿠폰 발급 수량 검증
    - 쿠폰 전체 발급 수량
    - 중복 발급 요청 검증 (중복 참여 방지)
  - 쿠폰 발급 (검증 후 발급)
    - 쿠폰 발급 수량 증가
    - 쿠폰 발급 기록 저장
      - 쿠폰 ID
      - 유저 ID

### 쿠폰 발급 기능 구현의 목표

- 정확한 발급 수량 제어 (동시성 이슈 처리)
- 높은 처리량
  - 복잡한 쿠폰 구조는 생략

----
## 사용 기술 스택

#### 로컬 환경

- JDK 17
- Spring Boot 3.2.2
- IDE: IntelliJ Ultimate edition
- DBMS: DataGrip
- Docker( Docker-compose, Docker desktop)

#### 배포 환경

- AWS EC2

### 기술 분류(개발)

- Spring Data JPA & QueryDsl
- Spring Data Redis
- Spring Actuator & Prometheus & Grafana (모니터링)

### 기술 분류(데이터)

#### 로컬 환경

- Mysql 8
- Redis 7
- H2

#### 배포 환경

- AWS RDS
- AWS Elasticache

### 성능 테스트

#### 로컬 환경

- Locust

#### 배포 환경

- 네트워크 트래픽 비용
