# Global Solutions FIAP - Sistema Integrado de Logística e Telemetria IoT

Este projeto foi desenvolvido como solução para a **Global Solutions da FIAP**. Trata-se de uma API REST robusta voltada para a gestão de cadeias de suprimentos, logística reversa/avançada e monitoramento de frotas através de dispositivos de rastreamento com sensores IoT embarcados.

---

## 🔄 Fluxo Lógico Operacional (Ordem de Execução)

A interface interativa do **Swagger UI** foi cirurgicamente projetada para contar a história do fluxo operacional de cima para baixo. Para realizar testes rápidos de ponta a ponta, siga esta exata ordem de execução:

### 🧱 A. Cadastro de Usuários
* Ponto inicial do ecossistema. Permite registrar os motoristas, operadores logísticos ou clientes.
* **Ação:** Execute o `POST /usuarios` para criar uma entidade e copie o `id` gerado.

### 🔐 B. Autenticação
* Camada corporativa de segurança. Valida credenciais e emite as permissões de acesso da API.
* **Ação:** Efetue o login no `POST /api/auth/login` utilizando os dados criados no passo anterior para obter o token de sessão.

### 📦 C. Gestão de Pedidos
* Onde as ordens de transporte ganham vida. Cria o manifesto da carga vinculando-o diretamente ao ID do usuário emissor/responsável.
* **Ação:** Informe o `usuarioId` na URL do `POST /pedidos/usuarios/{usuarioId}`. O corpo do JSON foi otimizado para solicitar apenas dados essenciais da carga (`descricao`, `status`), gerando um envio limpo e sem poluição de objetos aninhados.

### 📡 D. Rastreamento de Carga (Telemetria IoT)
* O coração do monitoramento. Simula os pacotes de dados enviados periodicamente via rede celular por um módulo IoT fixado ao veículo de transporte.
* **Ação:** Envie coordenadas e telemetria mecânica direto no `POST /rastreamentos/pedidos/{pedidoId}`. O Swagger exibirá exemplos reais prontos de sensores (`latitude`, `longitude`, `velocidade`, `nivelCombustivel`, `tipoConexao`).

---

## 🛠️ Matriz de Endpoints

| Módulo | Método | Endpoint | Função Logística |
| :--- | :---: | :--- | :--- |
| **A. Usuários** | `POST` | `/usuarios` | Registo de novas entidades operacionais no ecossistema. |
| **A. Usuários** | `GET` | `/usuarios` | Auditoria interna e listagem geral de utilizadores ativos. |
| **B. Autenticação**| `POST` | `/api/auth/login` | Autenticação central e geração de credenciais seguras. |
| **C. Pedidos** | `POST` | `/pedidos/usuarios/{usuarioId}` | Criação de carga vinculada ao ID do responsável. |
| **C. Pedidos** | `GET` | `/pedidos` | Monitoramento global de todas as ordens de despacho. |
| **C. Pedidos** | `PUT` | `/pedidos/{id}/status` | Transição de estado da entrega utilizando DTO customizado (`CONCLUIDO`). |
| **C. Pedidos** | `DELETE`| `/pedidos/{id}` | Exclusão física ou cancelamento de registros do fluxo. |
| **D. Rastreio** | `POST` | `/rastreamentos/pedidos/{pedidoId}` | Injeção contínua de telemetria de sensores IoT. |
| **D. Rastreio** | `GET` | `/rastreamentos/pedidos/{pedidoId}` | Histórico geográfico e mapa de rotas percorridas pela frota. |

---

## 🚀 Arquitetura e Otimizações de Engenharia Aplicadas

Para elevar o nível técnico da entrega e garantir notas máximas nos critérios de avaliação da FIAP, aplicamos as seguintes melhorias na arquitetura da API:

1. **Otimização de Payload via Swagger OpenAPI (`@Schema`):** Eliminamos a sobrecarga de payloads gigantescos no Swagger utilizando anotações de visibilidade. Dados como IDs gerados por UUID, datas automáticas de leitura e objetos mapeados por relacionamentos do JPA (`@ManyToOne`) foram ocultados no formulário de envio, deixando os JSONs de teste leves, objetivos e prontos com exemplos de clique único.
2. **Design Pattern Inner DTO (Classes Estáticas Internas):** Implementamos requests especializados isolados dentro de classes controladoras através do modificador `public static class Request`. Isso evita a criação excessiva de dezenas de arquivos de classes externas no projeto, mantendo uma organização limpa, de fácil manutenção e em conformidade com as boas práticas de mercado.
3. **Isolamento de Tipos de Dados:** Correção do comportamento padrão do Swagger que enviava strings puras com aspas duplas adicionais no corpo de requisições do tipo texto livre, mitigando erros de parse no banco de dados e padronizando enums do sistema.

---

## 💻 Como Executar o Projeto Localmente

Certifique-se de possuir o Java 17 instalado na sua máquina, abra o terminal e execute os passos:

```bash
# 1. Clonar o repositório
git clone https://github.com/dav-lucas/gs1-logistica-api

# 2. Entrar no diretório do projeto
cd gs1

# 3. Compilar a aplicação usando o Maven Wrapper
./mvnw clean package

# 4. Inicializar o Spring Boot
./mvnw spring-boot:run
