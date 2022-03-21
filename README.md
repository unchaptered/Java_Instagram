# Introduce

본 문서는 _2022년 12월 17일_ 에 작성되었습니다.

[Java_Instagram Git Repository](https://github.com/unchaptered/Java_Instagram)

## Instagram

본 프로젝트는 Instgram 을 모델로 삼아서 만들어진 Java 기반 프로젝트입니다.

DTO, DAO 등의 비즈니스 로직과 정규표현식과 같은 유효성 검사 등을 신경썻습니다.

<hr>

## Process

본 프로젝트는 다음과 같은 절차로 진행되었습니다.

1. DB Design
2. Business Logic Dev

### DB Design

RDBMS 인 MySQL 을 사용했으며, 2개의 Entity 를 만들었습니다.

#### User Entity

```sql
CREATE TABLE user (
 user_code SERIAL PRIMARY KEY,
 user_id VARCHAR(500) UNIQUE NOT NULL,
 user_email TEXT NOT NULL,
 user_pw TEXT NOT NULL,
 user_nick TEXT NOT NULL,
 user_phone VARCHAR(500) UNIQUE NOT NULL,
 user_post_list TEXT
);
```

#### Post Entity

```sql
CREATE TABLE post (
 post_code SERIAL PRIMARY KEY,
 post_text TEXT NOT NULL,
 post_tags TEXT NOT NULL,
 post_time DATE NOT NULL,
 post_owner INTEGER REFERENCES user,
 post_like_list TEXT
);
```

### Business Logic Dev

Instagram 은 다음과 같은 비즈니스 로직으로 구성되었습니다.

1. View - 뷰페이지와 비즈니스 로직이 합쳐져 있습니다.
2. EntityDTO - DB 의 Entity 에 해당하는 DTO 가 정의되어 있습니다.
3. EntityDAO - DB 의 Entity 의 정보를 제어하기 위한 DAO 가 정의되어 있습니다.
4. Singleton - Singleton 패턴을 이용해서 DBConnection, Session, Scanner 등을 구현했습니다.
5. Validation - 유효성 검사 구문을 모은 클래스를 정으했습니다.
6. Test - 테스트 데이터를 생성해주는 클래스를 구현했습니다.

<br>

## Contributors

1. unchaptered [Git Home](https://github.com/unchaptered) [Velog](https://velog.io/@unchapterd/)
2. kogyul [Git Home](https://github.com/kogyul) [Velog](https://velog.io/@kgyul12)
3. capriceksy [Git Home](https://github.com/capriceksy) [Velog](https://velog.io/@capriceksy)
4. sunsetkk [Git Home](https://github.com/sunsetkk) [T-story](https://guul.tistory.com)
5. kmhyeon [Git Home](https://github.com/kmhyeon)