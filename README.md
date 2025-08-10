# 예약 API 서버

간단한 예약 시스템을 위한 Spring Legacy API 서버입니다.

## 주요 기능

* **예약 생성 API:** POST `/api/bookings` - 새로운 예약을 생성합니다
* **테스트 API:** GET `/api/bookings/test` - 서버 상태 확인용 테스트 엔드포인트
* **MySQL 데이터베이스:** 예약 정보를 RDS MySQL 데이터베이스에 저장
* **Docker 자동 배포:** Docker를 통한 자동화된 배포 설정 완료

## 프로젝트 구조

```
src/main/java/org/bank/booking/
├── controller/     # REST API 컨트롤러
├── service/        # 비즈니스 로직
├── mapper/         # MyBatis 매퍼 인터페이스
├── domain/         # 도메인 모델 (VO)
├── dto/           # 데이터 전송 객체
└── config/        # JSON 타입 핸들러 등 설정
```

## API 엔드포인트

### 예약 생성
- **URL:** `POST /api/bookings`
- **Description:** 새로운 예약을 생성합니다
- **Content-Type:** `application/json`
- **Response:** `201 Created` (성공시)

**Request Body 예시:**
```json
{
  "bookingId": "BK001",
  "email": "user@example.com",
  "branchId": 1,
  "finPrdtCode": "FIN123456",
  "date": "2025-01-15",
  "time": "14:30",
  "docInfo": {
    "requiredDocuments": [
      "신분증 사본",
      "주민등록등본"
    ],
    "docList": [
      "소득증명서",
      "재직증명서"
    ]
  }
}
```

**필드 설명:**
- `bookingId`: 예약 고유 ID (문자열, 예: "BK001")
- `email`: 예약자 이메일 주소
- `branchId`: 지점 ID (숫자)
- `finPrdtCode`: 금융상품 코드
- `date`: 예약 날짜 (yyyy-MM-dd 형식)
- `time`: 예약 시간 (HH:mm 형식)
- `docInfo`: 서류 정보 객체
  - `requiredDocuments`: 필요 서류 목록 (문자열 배열)
  - `docList`: 추가 서류 목록 (문자열 배열)

### 상태 확인
- **URL:** `GET /api/bookings/test`
- **Description:** 서버 상태 확인
- **Response:** `"welcome"`


## 기술 스택

* Java 16
* Spring Framework 5.3.37
* MyBatis
* MySQL
* Gradle
* Lombok
* Jackson (JSON 처리)
* Docker (배포)

## 데이터베이스

### 연결 설정
`Github Secrets`에서 MySQL 데이터베이스 연결 정보를 설정합니다.

### 테이블 구조
**booking 테이블:**
```sql
CREATE TABLE booking (
    booking_id VARCHAR(255) PRIMARY KEY,
    branch_id INT NOT NULL,
    email VARCHAR(255) NOT NULL,
    fin_prdt_cd VARCHAR(100),
    date DATE NOT NULL,
    time VARCHAR(10),
    doc_info JSON
);
```

**데이터 저장 방식:**
- 예약 정보는 `booking` 테이블에 저장됩니다
- `docInfo` 객체는 JSON 형태로 `doc_info` 컬럼에 저장됩니다
- MyBatis의 커스텀 `JsonTypeHandler`를 사용하여 객체 ↔ JSON 변환을 처리합니다

## 배포

Docker를 통한 자동 배포가 설정되어 있습니다.