# Kessler Vision — Backend & Motor Físico

## Sobre a Solução
O **Kessler Vision** é um ecossistema computacional de alta performance projetado para o monitoramento tático de ativos espaciais e auditoria de riscos de colisão na Órbita Baixa da Terra (LEO). 

A arquitetura do backend foi desenhada seguindo o padrão de **Microserviços Decoplados**, dividida em duas frentes fundamentais:
1. **Orquestrador Core (Java Spring Boot):** Responsável pelas regras de negócio, governança de dados, persistência relacional robusta (PostgreSQL) e exposição de endpoints RESTful.
2. **Motor Físico Orbital (Python FastAPI):** Um microserviço analítico isolado que consome dados reais da API pública da NASA/CelesTrak (Nuvem de detritos do Cosmos 2251) executando algoritmos de busca e ordenação de alta performance em memória cache.

---

## O Problema Escolhido
A humanidade tornou-se profundamente dependente da infraestrutura orbital para telecomunicações, geolocalização (GPS), previsões meteorológicas e monitoramento climático. No entanto, mais de 60 anos de exploração espacial geraram uma nuvem massiva de lixo espacial. 

O projeto mitiga diretamente o risco da **Síndrome de Kessler** — um cenário hipotético onde a densidade de objetos em órbita é alta o suficiente para que colisões gerem um efeito cascata, destruindo satélites operacionais e tornando órbitas inteiras inutilizáveis por gerações. O Kessler Vision antecipa e audita esses riscos em milissegundos, permitindo manobras evasivas coordenadas.

---

## ODS Relacionado
Esta solução está diretamente alinhada com o **ODS 9: Indústria, Inovação e Infraestrutura** da Organização das Nações Unidas (ONU).

Ao proteger constelações de satélites comerciais e científicos contra impactos catastróficos, o Kessler Vision atua como uma camada tecnológica essencial para:
* Garantir a **resiliência da infraestrutura tecnológica** global.
* Promover a inovação através de algoritmos escaláveis de segurança espacial.
* Apoiar a sustentabilidade de longo prazo das atividades na órbita terrestre, protegendo investimentos e serviços públicos globais.

---

## 🏗️ Arquitetura e Estrutura do Projeto

```text
├── kessler-vision-java-api/          # Core da Aplicação (Spring Boot)
│   ├── src/main/java/br/com/kesslervision/api/
│   │   ├── controller/               # Portas de entrada REST (Endpoints)
│   │   ├── model/                    # Entidades relacionais, Abstrações e Interfaces (POO)
│   │   ├── repository/               # Camada JPA para acesso ao banco (PostgreSQL)
│   │   └── service/                  # Camada de inteligência e integração HTTP
│   └── src/main/resources/
│       └── application.properties    # Configurações do ambiente e conexão JDBC
│
└── kessler_vision.py                 # Motor de Física de Alta Performance (Python)
```

## Principais Endpoints da API (Java):

*   POST /api/empresas - Cadastra organizações controladoras.
    
*   POST /api/frotas - Agrupa satélites sob uma organização.
    
*   POST /api/satelites - Registra novos ativos espaciais com seu respectivo ID NORAD.
    
*   GET /api/radar/varredura/{idSatelite} - Ponto crítico que orquestra a leitura do banco, aciona o motor Python em tempo real e persiste o Alerta de Conjunção se houver perigo.

---

## Como Executar o Projeto
--------------------------

### Pré-requisitos Mínimos:

*   Java JDK 21 instalado.
    
*   Python 3.10 ou superior instalado.
    
*   PostgreSQL rodando localmente.

--- 

### 1\. Configuração do Banco de Dados

1.  Abra sua ferramenta de gerenciamento do banco (ex: pgAdmin ou DBeaver).
    
2.  Crie uma base de dados vazia chamada exatamente: kesslervision.
    
3.  Properties:
   ```text
    spring.datasource.url=jdbc:postgresql://localhost:5432/kesslervision
    spring.datasource.username=postgres
    spring.datasource.password=SUA\_SENHA\_AQUI
   ```

### 2\. Inicialização do Motor Físico (Python)

Abra o terminal na pasta onde o arquivo kessler\_vision.py está localizado e execute:

### Instalar os módulos web necessários
```text
pip install fastapi uvicorn requests
```

### Executar o microserviço
```text
python kessler_vision.py
```

O console exibirá o download da base CelesTrak em memória e informará que o servidor está online na porta **8000**.

### 3\. Inicialização da API Core (Java)

Abra a pasta do projeto Java no VS Code (ou sua IDE de preferência):

1.  Aguarde a sincronização das dependências do Maven (pom.xml).
    
2.  Abra o arquivo ApiApplication.java e clique no botão **Run** (ou execute mvn spring-boot:run no terminal).
    
3.  O Hibernate criará as 5 tabelas físicas automaticamente no seu banco de dados e iniciará o Tomcat na porta **8080**.
    

### 4\. Validando o Ecossistema (Teste de Integração)

Abra seu navegador de internet ou cliente de API (Postman/Thunder Client) e acesse:http://localhost:8080/api/radar/varredura/1

O fluxo completo de microserviços (Java -> DB -> Python FastAPI -> Algoritmo de Busca Binária -> Retorno ao Java -> Persistência de Alerta) será executado instantaneamente.

👥 Integrantes do Grupo
-----------------------

*   **\[Caio Nascimento Battista]** - RM: \[XXXXX\]
    
*   **\[Lucas Cavalcante]** - RM: \[XXXXX\]
    
*   **\[Matheus Rodrigues]** - RM: \[XXXXX\]
    
*   **\[Manoah Leão]** - RM: \[XXXXX\]
    
*   **\[Jean Pierre]** - RM: \[XXXXX\]
    

📺 Demonstração em Vídeo
------------------------

O vídeo completo com a explicação da arquitetura, defesa do problema escolhido e a execução prática da API funcionando de ponta a ponta pode ser assistido no link abaixo:

🔗 [**Clique aqui para assistir ao vídeo do projeto no YouTube**](https://www.youtube.com/)

_Desenvolvido como projeto acadêmico para avaliação corporativa e técnica._
