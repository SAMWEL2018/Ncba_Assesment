-- public.accounts definition

-- Drop table

-- DROP TABLE public.accounts;

CREATE TABLE public.accounts (
                                 id bigserial NOT NULL,
                                 account_id varchar(255) NOT NULL,
                                 customer_id int8 NULL,
                                 balance numeric(38, 2) DEFAULT 0.00 NOT NULL,
                                 loan_eligible bool DEFAULT false NULL,
                                 CONSTRAINT accounts_account_id_key UNIQUE (account_id),
                                 CONSTRAINT accounts_pkey PRIMARY KEY (id)
);


-- public.accounts foreign keys

ALTER TABLE public.accounts ADD CONSTRAINT accounts_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(id) ON DELETE CASCADE;

-- public.customers definition

-- Drop table

-- DROP TABLE public.customers;

CREATE TABLE public.customers (
                                  id bigserial NOT NULL,
                                  account_id varchar(255) NOT NULL,
                                  "name" varchar(255) NULL,
                                  email varchar(255) NOT NULL,
                                  "password" varchar(255) NOT NULL,
                                  status varchar(255) NOT NULL,
                                  CONSTRAINT customers_account_id_key UNIQUE (account_id),
                                  CONSTRAINT customers_email_key UNIQUE (email),
                                  CONSTRAINT customers_pkey PRIMARY KEY (id)
);


-- public.verification_codes definition

-- Drop table

-- DROP TABLE public.verification_codes;

CREATE TABLE public.verification_codes (
                                           id bigserial NOT NULL,
                                           email varchar(255) NOT NULL,
                                           code varchar(255) NOT NULL,
                                           expires_at timestamp NOT NULL,
                                           created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                           CONSTRAINT verification_codes_pkey PRIMARY KEY (id)
);
