<img width="100%" src="https://drive.google.com/uc?export=view&id=1NRxVmtRZeBGs3wJDdji3lGz0saCMpPz3">

# EIGHTEEN

**너도 몰랐던 너의 에이틴!**
에이틴은 노래방 노래 추천 플랫폼입니다.

<img width="100%" src="https://drive.google.com/uc?export=view&id=1ukX_tYtiwfPPDIYLul7ytMj1GdnmEjBZ">

---

## MAIN SERVICE

<img width="100%" src="https://drive.google.com/uc?export=view&id=1uIyP3eRu-a1fCsIuIhL9ZzoK7L-3HH8A">

### 1. 사용자 애창곡기반 노래 추천

### 2. 날씨·기분·상황별 노래 추천

### 3. 성별·연령별 애창곡 랭킹 제공

## Co-work tool

개발환경
형상관리 : Gitlab
이슈관리 : Jira
커뮤니케이션 : Mattermost, Notion
디자인 : Figma

## 🛠 기타 편의 툴

Postman

## Front-end Stack

- IDE : Visual Studio Code 1.75.1
- language
  - Typescript 4.9.4
  - node js 18.13.0
- Framework
  - react 18.2.0
  - react toolkit 1.9.1
- Http : Axios 1.2.3
- API
- router : react-router-dom 6.7.0
  state management tool
  react-redux 8.0.5
  reduxjs/toolkit 1.9.1

## Back-end Stack

IDE : Intellij IDEA 2020.3, visual studio
language : Java openjdk 17.0.6, python 3.8
Springboot 2.7.8
Spring Data JPA
Lombok
Spring Security
Spring Oauth2
Spring Web
Spring batch
Elk stack (ES, logstash, Kibana)
Build tool : Gradle 7.6
Swagger 2.9.2
com.auth0:java-jwt 3.10.2
Flask 2.2.3
MySQL With TLS
PyMySQL
scikit-learn
scikit-surprise
SQLAlchemy

DB
Mysql 8.0.32

Server

# ✨ 기술 특이점

## 🎯 MSA 설계

### 배포

서비스별 배포가 가능.(배포시 전체 서비스의 중단이 없음)
특정 서비스의 요구사항만 반영하여, 빠르게 배포 가능.
확장

다양하고, 새로운 서비스에 대한 유연한 확장 가능.
장애

일부 서비스의 장애가 전체 서비스로 확장될 가능성을 차단. (User service 에 해당하는 장애가 Owner service에 영향을 미치지 ❌)
부분적 장애에 대한 격리 처리 가능.

### CI/CD

gitlab runner으로 CI/CD를 gitlab에서 제어가능 & 팀원과 CI/CD 진행상황 공유가능

## 🎯 Elk stack

Elastic Search의 인덱싱 기능을 사용해서 검색속도를 높히면서 DB의 부하를 줄였다
초성 검색 등 세부적인 검색이 가능하다!

## 🎯 추천 알고리즘

1. Decision Tree
2. StandardScaler
3. GaussianNB
4. SVD
