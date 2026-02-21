--
-- PostgreSQL database dump
--

\restrict Npy2X7FmOINHy3I3ufdZQkXWIkwSOOCXVR3f5I5GkbZXvODLY5MvMex4VJxn1h6

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: advisers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.advisers (
    id integer NOT NULL,
    name character varying(50),
    department character varying(50)
);


ALTER TABLE public.advisers OWNER TO postgres;

--
-- Name: advisers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.advisers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.advisers_id_seq OWNER TO postgres;

--
-- Name: advisers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.advisers_id_seq OWNED BY public.advisers.id;


--
-- Name: studies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.studies (
    id integer NOT NULL,
    title character varying(50),
    description character varying(100)
);


ALTER TABLE public.studies OWNER TO postgres;

--
-- Name: studies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.studies_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.studies_id_seq OWNER TO postgres;

--
-- Name: studies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.studies_id_seq OWNED BY public.studies.id;


--
-- Name: supervises; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.supervises (
    adviser_id integer NOT NULL,
    study_id integer NOT NULL,
    student character varying(50),
    performance smallint,
    supervises_id integer NOT NULL
);


ALTER TABLE public.supervises OWNER TO postgres;

--
-- Name: supervises_supervises_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.supervises_supervises_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.supervises_supervises_id_seq OWNER TO postgres;

--
-- Name: supervises_supervises_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.supervises_supervises_id_seq OWNED BY public.supervises.supervises_id;


--
-- Name: advisers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advisers ALTER COLUMN id SET DEFAULT nextval('public.advisers_id_seq'::regclass);


--
-- Name: studies id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studies ALTER COLUMN id SET DEFAULT nextval('public.studies_id_seq'::regclass);


--
-- Name: supervises supervises_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supervises ALTER COLUMN supervises_id SET DEFAULT nextval('public.supervises_supervises_id_seq'::regclass);


--
-- Data for Name: advisers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.advisers (id, name, department) VALUES
	(1, 'Ahmed Hassan', 'Computer Science'),
	(2, 'Mona Ali', 'Information Systems'),
	(3, 'Omar Khaled', 'Software Engineering'),
	(4, 'Sara Mahmoud', 'Artificial Intelligence'),
	(5, 'Youssef Ibrahim', 'Data Science');


--
-- Data for Name: studies; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.studies (id, title, description) VALUES
	(1, 'AI Research', 'Machine Learning and Deep Learning'),
	(2, 'Database Optimization', 'Advanced SQL and Performance Tuning'),
	(3, 'Web Development', 'Full Stack Web Applications'),
	(4, 'Cyber Security', 'Network and Application Security'),
	(5, 'Mobile Apps', 'Android and iOS Development');


--
-- Data for Name: supervises; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.supervises (adviser_id, study_id, student, performance, supervises_id) VALUES
	(1, 1, 'Ahmed Ali', 85, 1),
	(1, 2, 'Sara Mohamed', 90, 2),
	(2, 1, 'Omar Hassan', 78, 3),
	(2, 3, 'Mona Khaled', 92, 4),
	(3, 2, 'Youssef Adel', 88, 5),
	(3, 3, 'Nour Ibrahim', 95, 6),
	(4, 1, 'Laila Mostafa', 81, 7);


--
-- Name: advisers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.advisers_id_seq', 5, true);


--
-- Name: studies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.studies_id_seq', 5, true);


--
-- Name: supervises_supervises_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.supervises_supervises_id_seq', 7, true);


--
-- Name: advisers advisers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advisers
    ADD CONSTRAINT advisers_pkey PRIMARY KEY (id);


--
-- Name: studies studies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studies
    ADD CONSTRAINT studies_pkey PRIMARY KEY (id);


--
-- Name: supervises supervises_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supervises
    ADD CONSTRAINT supervises_pkey PRIMARY KEY (supervises_id);


--
-- Name: supervises fk_adviser; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supervises
    ADD CONSTRAINT fk_adviser FOREIGN KEY (adviser_id) REFERENCES public.advisers(id) ON DELETE CASCADE;


--
-- Name: supervises fk_study; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supervises
    ADD CONSTRAINT fk_study FOREIGN KEY (study_id) REFERENCES public.studies(id) ON DELETE CASCADE;


--
-- Name: supervises supervises_adviser_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supervises
    ADD CONSTRAINT supervises_adviser_id_fkey FOREIGN KEY (adviser_id) REFERENCES public.advisers(id) ON DELETE CASCADE;


--
-- Name: supervises supervises_study_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.supervises
    ADD CONSTRAINT supervises_study_id_fkey FOREIGN KEY (study_id) REFERENCES public.studies(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

\unrestrict Npy2X7FmOINHy3I3ufdZQkXWIkwSOOCXVR3f5I5GkbZXvODLY5MvMex4VJxn1h6

