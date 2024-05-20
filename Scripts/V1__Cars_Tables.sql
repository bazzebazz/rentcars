DROP TABLE IF EXISTS "public"."cars";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS cars_id_seq;

-- Table Definition
CREATE TABLE "public"."cars" (
    "id" int8 NOT NULL DEFAULT nextval('cars_id_seq'::regclass),
    "version" int8,
    "available" bool NOT NULL,
    "brand" varchar(255) NOT NULL,
    "description" varchar(255) NOT NULL,
    "model" varchar(255) NOT NULL,
    "price" numeric(38,2) NOT NULL,
    "year" int4 NOT NULL,
    "category_id" int8,
    "price_id" int8,
    CONSTRAINT "fkl3nfu3v9mo1ec9fmb4pgyfyaj" FOREIGN KEY ("category_id") REFERENCES "public"."category"("id"),
    CONSTRAINT "fkje1u8lfn6o5104x8phwuwe4e2" FOREIGN KEY ("price_id") REFERENCES "public"."price"("id"),
    PRIMARY KEY ("id")
);


-- Indices
CREATE UNIQUE INDEX uk_p2mxx2b0gv19qkqi5rfp6isht ON public.cars USING btree (price_id);

DROP TABLE IF EXISTS "public"."cars_category";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS cars_category_id_seq;

-- Table Definition
CREATE TABLE "public"."cars_category" (
    "id" int8 NOT NULL DEFAULT nextval('cars_category_id_seq'::regclass),
    "version" int8,
    "cars_id" int8,
    "category_id" int8,
    CONSTRAINT "fk2dtkp202ejcpwy72115iv19vx" FOREIGN KEY ("cars_id") REFERENCES "public"."cars"("id"),
    CONSTRAINT "fknbq3f6p0al8f20bq6i9fryyx5" FOREIGN KEY ("category_id") REFERENCES "public"."category"("id"),
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."category";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS category_id_seq;

-- Table Definition
CREATE TABLE "public"."category" (
    "id" int8 NOT NULL DEFAULT nextval('category_id_seq'::regclass),
    "version" int8,
    "description" varchar(255),
    "name" varchar(255),
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."client";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS client_id_seq;

-- Table Definition
CREATE TABLE "public"."client" (
    "id" int8 NOT NULL DEFAULT nextval('client_id_seq'::regclass),
    "version" int8,
    "driver_license" varchar(255),
    "driver_license_category" varchar(255),
    "driver_license_expiration" varchar(255),
    "email" varchar(255),
    "last_name" varchar(255),
    "name" varchar(255),
    "password" varchar(255),
    "phone" int4,
    "token" varchar(255),
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."price";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS price_id_seq;

-- Table Definition
CREATE TABLE "public"."price" (
    "id" int8 NOT NULL DEFAULT nextval('price_id_seq'::regclass),
    "version" int8,
    "daily_price" numeric(38,2) NOT NULL,
    "discount_rate" numeric(38,2) NOT NULL,
    "is_in_discount" bool NOT NULL,
    "total_price" numeric(38,2),
    PRIMARY KEY ("id")
);

