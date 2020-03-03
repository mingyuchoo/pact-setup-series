# Pact Broker

이 프로젝트는 CDC(Consumer Driven Contract) Test를 위한 Pact Broker를 띄우는 목적입니다.

## 준비할 환경

- ubuntu 18.04 이상인 운영체제
- node v12 버전 이상 설치
- docker 설치
- docker-compose 설치

## 실행 방법

- `npm run docker:build`

## 접속 방법

- `http://localhost:9292`

## 고려 사항

pact broker를 기동하기 위해 PostgreSQL 서버를 5432 포트로 기동합니다.
DB와 Broker에 접속하기 위한 계정 정보는 docker-compose-pact-broker.yml을 참고하세요.
