/**
 * Generated by the Shiro plugin. This filters class protects all URLs
 * via access control by convention.
 */
class SecurityFilters {
    def filters = {
        base(uri: "/") {
            before = {
                // Ignore direct views (e.g. the default main index page).
                if (!controllerName) return true
            }
        }

        auth(controller: /\b(?!(item|book|basic|form)\b)\w+/, action: "*") {
            before = {
                accessControl(auth: true) { true }
            }
        }

        bookEditing(controller: "book", action: "*") {
            before = {
                accessControl {
                    role("Administrator")
                }
            }
        }

        test(controller: "test", action: "(create|edit|save|update|delete)") {
            before = {
                accessControl()
            }
        }

        wildcards(controller: "wildcard", action: "*") {
            before = {
                accessControl {
                    permission("w:$actionName")
                }
            }
        }
    }
}
