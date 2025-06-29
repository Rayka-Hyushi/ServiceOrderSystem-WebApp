--
-- PostgreSQL database dump
--

-- Dumped from database version 17.3
-- Dumped by pg_dump version 17.2

-- Started on 2025-06-25 11:56:31

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
-- TOC entry 217 (class 1259 OID 16387)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    idcliente integer NOT NULL,
    nome character varying,
    telefone character varying,
    email character varying,
    endereco character varying,
    idusuario integer NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16392)
-- Name: cliente_idcliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_idcliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cliente_idcliente_seq OWNER TO postgres;

--
-- TOC entry 4942 (class 0 OID 0)
-- Dependencies: 218
-- Name: cliente_idcliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_idcliente_seq OWNED BY public.cliente.idcliente;


--
-- TOC entry 219 (class 1259 OID 16393)
-- Name: ordens_servico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordens_servico (
    osid integer NOT NULL,
    idcliente integer,
    descricao character varying,
    valor_total double precision,
    estado character varying(21),
    desconto double precision,
    extras double precision,
    idusuario integer NOT NULL
);


ALTER TABLE public.ordens_servico OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16398)
-- Name: ordens_servico_osid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ordens_servico_osid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ordens_servico_osid_seq OWNER TO postgres;

--
-- TOC entry 4943 (class 0 OID 0)
-- Dependencies: 220
-- Name: ordens_servico_osid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ordens_servico_osid_seq OWNED BY public.ordens_servico.osid;


--
-- TOC entry 221 (class 1259 OID 16399)
-- Name: relacao_ordem_servico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.relacao_ordem_servico (
    rosid integer NOT NULL,
    osid integer,
    idservico integer
);


ALTER TABLE public.relacao_ordem_servico OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16402)
-- Name: servico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.servico (
    idservico integer NOT NULL,
    nome character varying,
    descricao character varying,
    valor double precision,
    idusuario integer NOT NULL
);


ALTER TABLE public.servico OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16407)
-- Name: view_os; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.view_os AS
 SELECT s.idservico,
    s.nome,
    s.descricao,
    s.valor,
    os.rosid,
    os.osid,
    os.idservico AS osservico
   FROM ((public.servico s
     JOIN public.relacao_ordem_servico os ON ((os.idservico = s.idservico)))
     JOIN public.ordens_servico o ON ((o.osid = os.osid)))
  WHERE (s.idusuario = o.idusuario);


ALTER VIEW public.view_os OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16411)
-- Name: recibo; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.recibo AS
 SELECT c.idcliente,
    c.nome,
    c.telefone,
    c.email,
    c.endereco,
    os.osid,
    os.idcliente AS oscliente,
    os.descricao AS osdesc,
    os.valor_total AS total,
    os.estado,
    os.desconto,
    os.extras,
    vos.idservico,
    vos.nome AS snome,
    vos.descricao AS sdesc,
    vos.valor
   FROM ((public.cliente c
     JOIN public.ordens_servico os ON ((os.idcliente = c.idcliente)))
     JOIN public.view_os vos ON ((vos.osid = os.osid)));


ALTER VIEW public.recibo OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16416)
-- Name: relacao_ordem_servico_rosid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.relacao_ordem_servico_rosid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.relacao_ordem_servico_rosid_seq OWNER TO postgres;

--
-- TOC entry 4944 (class 0 OID 0)
-- Dependencies: 225
-- Name: relacao_ordem_servico_rosid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.relacao_ordem_servico_rosid_seq OWNED BY public.relacao_ordem_servico.rosid;


--
-- TOC entry 226 (class 1259 OID 16417)
-- Name: servico_idservico_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.servico_idservico_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.servico_idservico_seq OWNER TO postgres;

--
-- TOC entry 4945 (class 0 OID 0)
-- Dependencies: 226
-- Name: servico_idservico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.servico_idservico_seq OWNED BY public.servico.idservico;


--
-- TOC entry 227 (class 1259 OID 16418)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    email character varying(150) NOT NULL,
    senha text NOT NULL,
    foto_perfil bytea
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16423)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 4946 (class 0 OID 0)
-- Dependencies: 228
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 229 (class 1259 OID 16512)
-- Name: view_oc; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.view_oc AS
 SELECT c.idcliente,
    c.nome,
    c.telefone,
    c.email,
    c.endereco,
    os.osid,
    os.idcliente AS oscliente,
    os.descricao AS osdesc,
    os.valor_total AS total,
    os.estado,
    os.desconto,
    os.extras
   FROM (public.cliente c
     JOIN public.ordens_servico os ON ((os.idcliente = c.idcliente)))
  WHERE (c.idusuario = os.idusuario);


ALTER VIEW public.view_oc OWNER TO postgres;

--
-- TOC entry 4766 (class 2604 OID 16428)
-- Name: cliente idcliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN idcliente SET DEFAULT nextval('public.cliente_idcliente_seq'::regclass);


--
-- TOC entry 4767 (class 2604 OID 16429)
-- Name: ordens_servico osid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordens_servico ALTER COLUMN osid SET DEFAULT nextval('public.ordens_servico_osid_seq'::regclass);


--
-- TOC entry 4768 (class 2604 OID 16430)
-- Name: relacao_ordem_servico rosid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacao_ordem_servico ALTER COLUMN rosid SET DEFAULT nextval('public.relacao_ordem_servico_rosid_seq'::regclass);


--
-- TOC entry 4769 (class 2604 OID 16431)
-- Name: servico idservico; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servico ALTER COLUMN idservico SET DEFAULT nextval('public.servico_idservico_seq'::regclass);


--
-- TOC entry 4770 (class 2604 OID 16432)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 4772 (class 2606 OID 16434)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);


--
-- TOC entry 4780 (class 2606 OID 16436)
-- Name: usuario email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT email UNIQUE (email);


--
-- TOC entry 4774 (class 2606 OID 16438)
-- Name: ordens_servico ordens_servico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordens_servico
    ADD CONSTRAINT ordens_servico_pkey PRIMARY KEY (osid);


--
-- TOC entry 4776 (class 2606 OID 16440)
-- Name: relacao_ordem_servico relacao_ordem_servico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacao_ordem_servico
    ADD CONSTRAINT relacao_ordem_servico_pkey PRIMARY KEY (rosid);


--
-- TOC entry 4778 (class 2606 OID 16442)
-- Name: servico servico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servico
    ADD CONSTRAINT servico_pkey PRIMARY KEY (idservico);


--
-- TOC entry 4782 (class 2606 OID 16444)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 4784 (class 2606 OID 16445)
-- Name: ordens_servico cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordens_servico
    ADD CONSTRAINT cliente_fkey FOREIGN KEY (idcliente) REFERENCES public.cliente(idcliente) NOT VALID;


--
-- TOC entry 4786 (class 2606 OID 16450)
-- Name: relacao_ordem_servico idservico_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacao_ordem_servico
    ADD CONSTRAINT idservico_fkey FOREIGN KEY (idservico) REFERENCES public.servico(idservico) ON DELETE CASCADE;


--
-- TOC entry 4783 (class 2606 OID 16497)
-- Name: cliente idusuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT idusuario FOREIGN KEY (idusuario) REFERENCES public.usuario(id);


--
-- TOC entry 4788 (class 2606 OID 16502)
-- Name: servico idusuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servico
    ADD CONSTRAINT idusuario FOREIGN KEY (idusuario) REFERENCES public.usuario(id);


--
-- TOC entry 4785 (class 2606 OID 16507)
-- Name: ordens_servico idusuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordens_servico
    ADD CONSTRAINT idusuario FOREIGN KEY (idusuario) REFERENCES public.usuario(id);


--
-- TOC entry 4787 (class 2606 OID 16455)
-- Name: relacao_ordem_servico osid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacao_ordem_servico
    ADD CONSTRAINT osid_fkey FOREIGN KEY (osid) REFERENCES public.ordens_servico(osid);


-- Completed on 2025-06-25 11:56:33

--
-- PostgreSQL database dump complete
--

