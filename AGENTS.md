# Repository Guidelines

## Project Structure & Module Organization

This Maven multi-module library wraps WeChat Work (企业微信) server-side REST APIs for Spring Boot. Callbacks and frontend JSSDK are out of scope.

- `qywx-spring-boot-starter` — consumer-facing starter.
- `qywx-spring-boot-autoconfigurator` — autoconfiguration and `qywx.*` properties.
- `qywx-spring-boot-api` — services, Feign clients in `client/`, and support code.
- `qywx-spring-boot-model` — DTOs, enums, exceptions, and responses.
- `qywx-spring-boot-example` — standalone examples and integration tests.

Java code uses package root `com.github.shuaidd` and the standard `src/main/java` layout.

## Build, Test, and Development Commands

- `mvn -B package` — build the reactor and run library tests, matching CI.
- `mvn -B -DskipTests clean package` — fast build.
- `mvn -pl qywx-spring-boot-api -am package` — rebuild one module with dependencies.
- `mvn -B -f qywx-spring-boot-example/pom.xml test` — run example integration tests.

## Coding Style & Naming Conventions

Use Java 8 syntax, 4-space indentation, and existing formatting; no formatter is enforced. Packages are lowercase, classes are PascalCase, services end in `Service`, and tests end in `Test`. Reuse existing models before adding new request/response classes.

## Testing Guidelines

Library tests use JUnit. Example tests are integration tests that call the real WeChat Work API and require local credentials; never commit credentials. Add tests for new or changed API methods and run the relevant Maven commands before review.

## API and Changelog Maintenance

- API catalog: https://developer.work.weixin.qq.com/document/path/90664
- Update log: https://developer.work.weixin.qq.com/document/path/93221
- Check `CHANGELOG-PROCESSED.md` before work; after completing an entry, record its date, change, modules, and commit/PR.
- Keep `API-COVERAGE.md` current: mark implemented pages as ✅ 已对接 and record evidence in `client/`.

## Commit & Pull Request Guidelines

Use short Chinese intent summaries, matching history (for example, `接入文档管理功能`). PRs must state the API area, evidence, and build/test results, and link related issues. Do not commit generated analysis files or credentials.
