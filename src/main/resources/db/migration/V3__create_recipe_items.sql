CREATE TABLE IF NOT EXISTS recipe_items (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    recipe_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    UNIQUE recipe_item (recipe_id, item_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES item(id) ON UPDATE CASCADE ON DELETE CASCADE
);