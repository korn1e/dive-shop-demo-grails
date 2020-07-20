import org.ajigile.diveshop.DiveShopUtil

// Place your Spring DSL code here
beans = {
    diveShopUtil(org.ajigile.diveshop.DiveShopUtil) {
        diveShopService = ref("diveShopService")
    }

}
