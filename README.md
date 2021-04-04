# Grade_Management
웹 개발자로서의 포트폴리오 및 취업을 위해 제작중인 개인 프로젝트 입니다.

## 기술 스택
- Java (JDK >= 8)
- Spring boot
- Spring Data JPA
- JSP/Servlet
- H2 Database (추후 MySQL, Oracle 등으로 변경예정)
- BootStrap
- AWS

## 프로젝트 개요
데이터를 입력받은 학생들의 성적 현황 및 관리를 위한 웹 사이트를 개발해보자.

## 개발 현황
- 2020.12.22
  - 요구사항 분석 및 도메인, 테이블 설계 완료

- 2020.12.23
  - 학생, 성적 및 분반 클래스 및 테이블 생성 완료

- 2020.12.28
  - 각 도메인 테이블 필드 Not Null 제약 조건 지정 완료
  - Student 테이블 기본키 매핑 전략 제거 (기본키 값을 직접 입력해야 함)
