CREATE TABLE IF NOT EXISTS Products
(
  pid int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  cat  VARCHAR(50) NOT NULL,
  PRIMARY KEY (pid)
);

CREATE TABLE IF NOT EXISTS Clients
(
  cname    VARCHAR(50) PRIMARY KEY NOT NULL,
  password VARCHAR(50)             NOT NULL,
  country  VARCHAR(50)             NOT NULL
);

CREATE TABLE IF NOT EXISTS Stores
(
  sid      int(11) NOT NULL AUTO_INCREMENT,
  name     VARCHAR(50)             NOT NULL,
  category VARCHAR(50)             NOT NULL,
  PRIMARY KEY (sid)
);

CREATE TABLE IF NOT EXISTS StoreProducts
(
  sid    int(11) NOT NULL,
  pid    int(11) NOT NULL,
  amount INT         NOT NULL,
  price  INT         NOT NULL,
  PRIMARY KEY (sid, pid),
  FOREIGN KEY (sid) REFERENCES Stores (sid),
  FOREIGN KEY (pid) REFERENCES Products (pid)
);

CREATE TABLE IF NOT EXISTS Carts
(
  cname  VARCHAR(50) NOT NULL,
  sid    int(11) NOT NULL,
  pid    int(11) NOT NULL,
  amount INT         NOT NULL,
  PRIMARY KEY (cname, sid, pid),
  FOREIGN KEY (cname) REFERENCES Clients (cname),
  FOREIGN KEY (sid) REFERENCES StoreProducts (sid),
  FOREIGN KEY (pid) REFERENCES StoreProducts (pid)
);

CREATE TABLE IF NOT EXISTS Owners
(
  assigner VARCHAR(50) NOT NULL,
  assignee VARCHAR(50) NOT NULL,
  sid      int(11) NOT NULL,
  PRIMARY KEY (assigner, assignee, sid),
  FOREIGN KEY (assigner) REFERENCES Clients (cname),
  FOREIGN KEY (assignee) REFERENCES Clients (cname),
  FOREIGN KEY (sid) REFERENCES StoreProducts (sid)
);

CREATE TABLE IF NOT EXISTS Managers
(
  assigner            VARCHAR(50) NOT NULL,
  assignee            VARCHAR(50) NOT NULL,
  sid                 int(11) NOT NULL,
  PRODUCTS            BOOLEAN DEFAULT FALSE,
  POLICY              BOOLEAN DEFAULT FALSE,
  OWNER_ASSIGNMENT    BOOLEAN DEFAULT FALSE,
  MANAGERS_ASSIGNMENT BOOLEAN DEFAULT FALSE,
  CLOSE_STORE         BOOLEAN DEFAULT FALSE,
  FAQ                 BOOLEAN DEFAULT FALSE,
  HISTORY             BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (assigner, assignee, sid),
  FOREIGN KEY (assigner) REFERENCES Clients (cname),
  FOREIGN KEY (assignee) REFERENCES Clients (cname),
  FOREIGN KEY (sid) REFERENCES StoreProducts (sid)
);

CREATE TABLE IF NOT EXISTS Policies
(
  sid        int(11) NOT NULL,
  id         int(11) NOT NULL AUTO_INCREMENT,
  policyJson TEXT        NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (sid) REFERENCES StoreProducts (sid)
);

CREATE TABLE IF NOT EXISTS Discounts
(
  sid          int(11) NOT NULL,
  id           int(11) NOT NULL AUTO_INCREMENT,
  discountJson TEXT        NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (sid) REFERENCES StoreProducts (sid)
);