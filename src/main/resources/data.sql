INSERT INTO product (product_id, name, category, price, stock, sales, description, created_at)
VALUES (UNHEX(REPLACE('d6f9c4bd-8f4e-4c1f-8a2b-eae9a9decf1b', '-', '')), '유기농 당근', 'FRUITS_AND_VEGETABLES', 3000, 50, 0,
        '신선한 유기농 당근입니다.', NOW()),
       (UNHEX(REPLACE('d7aa2d4e-7c14-4e22-8a56-d3d19ed4d5ce', '-', '')), '감자', 'FRUITS_AND_VEGETABLES', 2000, 100, 0,
        '강원도에서 재배한 감자입니다.', NOW()),
       (UNHEX(REPLACE('f1e4c7c1-6b07-48c1-9103-e9a3c678e9b9', '-', '')), '청포도', 'FRUITS_AND_VEGETABLES', 15000, 30, 0,
        '상큼한 청포도입니다.', NOW()),
       (UNHEX(REPLACE('a2d2c1e9-6b07-4e88-a4d6-e9b9c678e9b9', '-', '')), '토마토', 'FRUITS_AND_VEGETABLES', 7000, 60, 0,
        '달콤한 토마토입니다.', NOW()),
       (UNHEX(REPLACE('c3f1e4d1-5b05-4c21-9111-d4a2c678e9b9', '-', '')), '사과', 'FRUITS_AND_VEGETABLES', 12000, 40, 0,
        '청송 사과입니다.', NOW());

INSERT INTO member (member_id, name, email, address, created_at, updated_at)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '홍길동', 'hong@test.com', '서울특별시 어딘가', NOW(), NULL),
       (UNHEX(REPLACE(UUID(), '-', '')), '김철수', 'kim@test.com', '부산광역시 저기', NOW(), NULL),
       (UNHEX(REPLACE(UUID(), '-', '')), '이영희', 'lee@test.com', '대구광역시 여기저기', NOW(), NULL),
       (UNHEX(REPLACE(UUID(), '-', '')), '박지민', 'park@test.com', '인천광역시 그곳', NOW(), NULL),
       (UNHEX(REPLACE(UUID(), '-', '')), '최수연', 'choi@test.com', '광주광역시 이곳저곳', NOW(), NULL);
