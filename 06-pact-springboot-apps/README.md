# Pact를 이용한 Consumer Driven Contract Test 예제

## 영향받은 사상

- Lean
  - 지속적인 개선
  - 가져가는 형태의 시스템 (Pull System)

- Agile
  - 지속적으로 동작하는 소프트웨어를 제공
  - 변화/변경에 대응

- Scrum
  - 여러 개의 사이클로 이뤄진 계획, 구축, 모니터링

- XP
  - 테스트 주도 개발 (TDD)
  - 행위 주도 개발 (BDD)


## Contract를 맞춰가는 개발 순서

1. 소비자(Consumer)쪽이 사용할 API 명세와 해당 명세에 대한 자가진단 테스트 코드를 작성
2. 소비자가 자체 테스트 통과 후 나온 Contract 파일을 공유(Pact에서는 Pact Broker에 등록)
3. 제공자(Provider)는 Contract 검증 먼저 실행 (TDD)
4. 제공자는 Contract이 실패하니 검증 통과를 위해 기능 코드 작성
5. 제공자는 Contract 재검증하여 통과 시킴
