# ==============================================================================
# 4GForce :: Version Management & Release
# ==============================================================================
#
# Signing setup:
#   Place the signing keystore at keys/release.jks.
#   Copy keys/release.properties.example to keys/release.properties and set:
#     storePassword=your-store-password
#     keyAlias=albertolicea00
#     keyPassword=your-key-password
#   In .properties values, write literal backslashes as \\ (Java escape syntax).
#   Both files are ignored by Git. Back up the keystore; future updates must
#   use the same signing key.
#
# Release output:
#   make release generates:
#     app/build/outputs/apk/release/4gforce-v<versionName>-release.apk
#     app/build/outputs/apk/release/4gforce-v<versionName>-release.apk.sha256
#   Gradle and Android Studio also use this APK name when building a release.
#
# Manual Release Workflow:
# ------------------------
# 1. Update version:
#      make bump-patch   # 1.0.0 -> 1.0.1
#      make bump-minor   # 1.0.0 -> 1.1.0
#      make bump-major   # 1.0.0 -> 2.0.0
#
# 2. Commit the version bump:
#      git commit -am "chore: bump version to v$(VERSION_NAME)"
#      git push origin main
#
# 3. Create and push the Git tag:
#      git tag -a "v$(VERSION_NAME)" -m "Release v$(VERSION_NAME)"
#      git push origin "v$(VERSION_NAME)"
#
# 4. Build release APK:
#      make release
#
# 5. Publish Release on GitHub:
#      gh release create "v$(VERSION_NAME)" \
#          app/build/outputs/apk/release/4gforce-v$(VERSION_NAME)-release.apk \
#          app/build/outputs/apk/release/4gforce-v$(VERSION_NAME)-release.apk.sha256 \
#          --title "v$(VERSION_NAME)" --notes "..."
# ==============================================================================

GRADLE_FILE := app/build.gradle.kts

VERSION_NAME := $(shell grep 'versionName = ' $(GRADLE_FILE) | head -1 | sed 's/.*"\(.*\)".*/\1/')
VERSION_CODE := $(shell grep 'versionCode = ' $(GRADLE_FILE) | head -1 | sed 's/[^0-9]//g')

MAJOR := $(word 1,$(subst ., ,$(VERSION_NAME)))
MINOR := $(word 2,$(subst ., ,$(VERSION_NAME)))
PATCH := $(word 3,$(subst ., ,$(VERSION_NAME)))

APK_DEBUG   := app/build/outputs/apk/debug/app-debug.apk
APK_RELEASE := app/build/outputs/apk/release/4gforce-v$(VERSION_NAME)-release.apk

.PHONY: help version build release clean bump-patch bump-minor bump-major

help: ## Show available commands
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2}'

version: ## Show current version
	@echo "v$(VERSION_NAME) (code $(VERSION_CODE))"

build: ## Build debug APK
	./gradlew assembleDebug
	@echo "Debug APK: $(APK_DEBUG)"

release: ## Build signed release APK and SHA-256 checksum
	./gradlew assembleRelease
	cd "$(dir $(APK_RELEASE))" && shasum -a 256 "$(notdir $(APK_RELEASE))" > "$(notdir $(APK_RELEASE)).sha256"
	@echo "Release APK: $(APK_RELEASE)"

clean: ## Clean build artifacts
	./gradlew clean

bump-patch: ## Bump patch version (1.0.0 → 1.0.1)
	$(eval NEW_VERSION := $(MAJOR).$(MINOR).$(shell echo $$(($(PATCH)+1))))
	$(eval NEW_CODE := $(shell echo $$(($(VERSION_CODE)+1))))
	@sed -i '' 's/versionCode = $(VERSION_CODE)/versionCode = $(NEW_CODE)/' $(GRADLE_FILE)
	@sed -i '' 's/versionName = "$(VERSION_NAME)"/versionName = "$(NEW_VERSION)"/' $(GRADLE_FILE)
	@echo "$(VERSION_NAME) → $(NEW_VERSION) (code $(NEW_CODE))"

bump-minor: ## Bump minor version (1.0.x → 1.1.0)
	$(eval NEW_VERSION := $(MAJOR).$(shell echo $$(($(MINOR)+1))).0)
	$(eval NEW_CODE := $(shell echo $$(($(VERSION_CODE)+1))))
	@sed -i '' 's/versionCode = $(VERSION_CODE)/versionCode = $(NEW_CODE)/' $(GRADLE_FILE)
	@sed -i '' 's/versionName = "$(VERSION_NAME)"/versionName = "$(NEW_VERSION)"/' $(GRADLE_FILE)
	@echo "$(VERSION_NAME) → $(NEW_VERSION) (code $(NEW_CODE))"

bump-major: ## Bump major version (1.x.x → 2.0.0)
	$(eval NEW_VERSION := $(shell echo $$(($(MAJOR)+1))).0.0)
	$(eval NEW_CODE := $(shell echo $$(($(VERSION_CODE)+1))))
	@sed -i '' 's/versionCode = $(VERSION_CODE)/versionCode = $(NEW_CODE)/' $(GRADLE_FILE)
	@sed -i '' 's/versionName = "$(VERSION_NAME)"/versionName = "$(NEW_VERSION)"/' $(GRADLE_FILE)
	@echo "$(VERSION_NAME) → $(NEW_VERSION) (code $(NEW_CODE))"
