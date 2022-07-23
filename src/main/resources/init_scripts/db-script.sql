create table if not exists Server(
    id INT AUTO_INCREMENT PRIMARY KEY,
    ipAddress varchar(100),
    name varchar(1000),
    memory varchar(1000),
    type varchar(1000),
    imageUrl varchar(1000),
    status varchar(1000),
    creation_date timestamp DEFAULT CURRENT_TIMESTAMP,
    updation_date timestamp DEFAULT CURRENT_TIMESTAMP
    );