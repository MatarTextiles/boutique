-- Categories
INSERT IGNORE INTO categories (id, name_fr, name_en, slug, description_fr, description_en, image_url, display_order, created_at, updated_at) VALUES
(1, 'Laine', 'Wool', 'laine', 'Laines de qualité pour tricot et confection', 'Quality wool for knitting and garment making', '/images/categories/laine.jpg', 1, NOW(), NOW()),
(2, 'Peau de Soie', 'Peau de Soie', 'peau-de-soie', 'Tissu satiné lisse et luxueux', 'Smooth and luxurious satin fabric', '/images/categories/peau-de-soie.jpg', 2, NOW(), NOW()),
(3, 'Coton', 'Cotton', 'coton', 'Cotons de toutes qualités pour vos projets', 'Cotton fabrics of all qualities for your projects', '/images/categories/coton.jpg', 3, NOW(), NOW()),
(4, 'Poly-Coton', 'Poly-Cotton', 'poly-coton', 'Mélange polyester-coton résistant et facile d''entretien', 'Durable and easy-care polyester-cotton blend', '/images/categories/poly-coton.jpg', 4, NOW(), NOW()),
(5, 'Boutons', 'Buttons', 'boutons', 'Grand choix de boutons pour tous vos besoins', 'Wide selection of buttons for all your needs', '/images/categories/boutons.jpg', 5, NOW(), NOW()),
(6, 'Tissus de Soirée et Mariage', 'Evening & Wedding Fabrics', 'tissus-soiree-mariage', 'Tissus élégants pour robes de soirée et mariages', 'Elegant fabrics for evening gowns and weddings', '/images/categories/tissus-soiree-mariage.jpg', 6, NOW(), NOW()),
(7, 'Fermeture Éclair', 'Zippers', 'fermeture-eclair', 'Fermetures éclair de toutes tailles et couleurs', 'Zippers in all sizes and colors', '/images/categories/fermeture-eclair.jpg', 7, NOW(), NOW()),
(8, 'Lin', 'Linen', 'lin', 'Lin naturel pour vêtements et décoration', 'Natural linen for clothing and decoration', '/images/categories/lin.jpg', 8, NOW(), NOW()),
(9, 'Organsa', 'Organza', 'organsa', 'Organsa léger et transparent pour créations délicates', 'Light and sheer organza for delicate creations', '/images/categories/organsa.jpg', 9, NOW(), NOW());

-- Products
INSERT IGNORE INTO products (id, category_id, name_fr, name_en, slug, description_fr, description_en, price, image_url, sku, stock_quantity, active, created_at, updated_at) VALUES
-- Laine
(1, 1, 'Laine Mérinos Fine', 'Fine Merino Wool', 'laine-merinos-fine', 'Laine mérinos extra-fine, douce et chaude, idéale pour tricots et vêtements d''hiver.', 'Extra-fine merino wool, soft and warm, ideal for knitwear and winter garments.', 18.99, '/images/products/laine-merinos.jpg', 'LAI-001', 200, true, NOW(), NOW()),
(2, 1, 'Laine Alpaga Douce', 'Soft Alpaca Wool', 'laine-alpaga-douce', 'Laine d''alpaga naturelle, hypoallergénique et incroyablement douce.', 'Natural alpaca wool, hypoallergenic and incredibly soft.', 24.99, '/images/products/laine-alpaga.jpg', 'LAI-002', 120, true, NOW(), NOW()),
-- Peau de Soie
(3, 2, 'Peau de Soie Ivoire', 'Ivory Peau de Soie', 'peau-de-soie-ivoire', 'Peau de soie ivoire au toucher soyeux, parfaite pour robes de mariée.', 'Ivory peau de soie with silky touch, perfect for wedding dresses.', 35.99, '/images/products/peau-soie-ivoire.jpg', 'PDS-001', 80, true, NOW(), NOW()),
(4, 2, 'Peau de Soie Champagne', 'Champagne Peau de Soie', 'peau-de-soie-champagne', 'Peau de soie couleur champagne, éclat subtil pour occasions spéciales.', 'Champagne-colored peau de soie, subtle sheen for special occasions.', 38.99, '/images/products/peau-soie-champagne.jpg', 'PDS-002', 60, true, NOW(), NOW()),
-- Coton
(5, 3, 'Coton Égyptien Premium', 'Premium Egyptian Cotton', 'coton-egyptien-premium', 'Tissu en coton égyptien de la plus haute qualité, parfait pour la confection de vêtements élégants.', 'Highest quality Egyptian cotton fabric, perfect for making elegant garments.', 24.99, '/images/products/coton-egyptien.jpg', 'COT-001', 150, true, NOW(), NOW()),
(6, 3, 'Coton Brodé Anglais', 'English Embroidered Cotton', 'coton-brode-anglais', 'Coton brodé avec motifs ajourés, idéal pour blouses et robes d''été.', 'Embroidered cotton with openwork patterns, ideal for blouses and summer dresses.', 28.50, '/images/products/coton-brode.jpg', 'COT-002', 90, true, NOW(), NOW()),
-- Poly-Coton
(7, 4, 'Poly-Coton Uni', 'Solid Poly-Cotton', 'poly-coton-uni', 'Tissu poly-coton uni disponible en plusieurs couleurs, facile d''entretien.', 'Solid poly-cotton fabric available in multiple colors, easy care.', 12.99, '/images/products/poly-coton-uni.jpg', 'PCO-001', 300, true, NOW(), NOW()),
(8, 4, 'Poly-Coton Imprimé Floral', 'Floral Print Poly-Cotton', 'poly-coton-floral', 'Poly-coton avec jolis motifs floraux, parfait pour vêtements et déco.', 'Poly-cotton with beautiful floral patterns, perfect for garments and decor.', 15.99, '/images/products/poly-coton-floral.jpg', 'PCO-002', 180, true, NOW(), NOW()),
-- Boutons
(9, 5, 'Boutons Nacre Naturels (lot de 10)', 'Natural Mother of Pearl Buttons (set of 10)', 'boutons-nacre-naturels', 'Lot de 10 boutons en nacre véritable, éclat naturel et élégant.', 'Set of 10 genuine mother of pearl buttons, natural and elegant sheen.', 8.99, '/images/products/boutons-nacre.jpg', 'BTN-001', 250, true, NOW(), NOW()),
(10, 5, 'Boutons Fantaisie Assortis (lot de 20)', 'Assorted Fancy Buttons (set of 20)', 'boutons-fantaisie-assortis', 'Lot de 20 boutons fantaisie de formes et couleurs variées.', 'Set of 20 fancy buttons in various shapes and colors.', 6.99, '/images/products/boutons-fantaisie.jpg', 'BTN-002', 300, true, NOW(), NOW()),
-- Tissus de Soirée et Mariage
(11, 6, 'Tulle Pailleté', 'Sequined Tulle', 'tulle-paillete', 'Tulle orné de paillettes scintillantes pour robes de soirée spectaculaires.', 'Tulle adorned with sparkling sequins for spectacular evening gowns.', 42.99, '/images/products/tulle-paillete.jpg', 'TSM-001', 70, true, NOW(), NOW()),
(12, 6, 'Satin Duchesse', 'Duchess Satin', 'satin-duchesse', 'Satin duchesse épais et lustré, le choix classique pour robes de mariée.', 'Thick and lustrous duchess satin, the classic choice for wedding dresses.', 49.99, '/images/products/satin-duchesse.jpg', 'TSM-002', 55, true, NOW(), NOW()),
-- Fermeture Éclair
(13, 7, 'Fermetures Éclair Invisibles (lot de 5)', 'Invisible Zippers (set of 5)', 'fermetures-eclair-invisibles', 'Lot de 5 fermetures éclair invisibles 20cm, couleurs assorties.', 'Set of 5 invisible zippers 20cm, assorted colors.', 7.49, '/images/products/fermetures-invisibles.jpg', 'FER-001', 400, true, NOW(), NOW()),
(14, 7, 'Fermeture Éclair Séparable 60cm', 'Separating Zipper 60cm', 'fermeture-eclair-separable', 'Fermeture éclair séparable robuste, idéale pour vestes et manteaux.', 'Sturdy separating zipper, ideal for jackets and coats.', 4.99, '/images/products/fermeture-separable.jpg', 'FER-002', 350, true, NOW(), NOW()),
-- Lin
(15, 8, 'Lin Français Naturel', 'Natural French Linen', 'lin-francais-naturel', 'Lin de qualité supérieure en provenance de France, teinte naturelle.', 'Superior quality linen from France, natural shade.', 32.50, '/images/products/lin-francais.jpg', 'LIN-001', 120, true, NOW(), NOW()),
(16, 8, 'Lin Lavé Couleurs', 'Washed Linen Colors', 'lin-lave-couleurs', 'Lin pré-lavé au toucher souple, disponible en plusieurs coloris.', 'Pre-washed linen with soft feel, available in multiple colors.', 29.99, '/images/products/lin-lave.jpg', 'LIN-002', 100, true, NOW(), NOW()),
-- Organsa
(17, 9, 'Organsa Cristal', 'Crystal Organza', 'organsa-cristal', 'Organsa cristal transparent et brillant, parfait pour décorations et voiles.', 'Transparent and shiny crystal organza, perfect for decorations and veils.', 16.99, '/images/products/organsa-cristal.jpg', 'ORG-001', 150, true, NOW(), NOW()),
(18, 9, 'Organsa Brodé', 'Embroidered Organza', 'organsa-brode', 'Organsa avec broderies raffinées, idéal pour tenues de cérémonie.', 'Organza with refined embroidery, ideal for formal wear.', 34.99, '/images/products/organsa-brode.jpg', 'ORG-002', 65, true, NOW(), NOW());
