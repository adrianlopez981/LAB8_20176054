package com.example.lab8_20176054.Controller;


import com.example.lab8_20176054.Entity.evento;
import com.example.lab8_20176054.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController //me añade el @ResponseBody
@CrossOrigin // para evitar bloqueos
@RequestMapping(value = "/evento")
public class ControllerBase {

//    @Autowired
//    DaoEmpresa daoEmpresa;


    @Autowired
    EventoRepository eventoRepository;
//
//    @Autowired
//    SupplierDao supplierDao;


    @GetMapping({""})
    public List<evento> listarEventos() {

        return eventoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarxId(@RequestParam(value = "id", required = false) int idParam) {

        HashMap<String, Object> responseMap = new HashMap<>();

//        if (idParam == null || idParam.isEmpty()) {
//            responseMap.put("estado", "error");
//            responseMap.put("msg", "Debe enviar el parámetro 'idSolicitud'");
//            return ResponseEntity.badRequest().body(responseMap);
//        }

        try {
            int id = Integer.parseInt(idParam);

            System.out.println(id);
            System.out.println(idParam);

            if (id > 0) {
                Optional<evento> opt = eventoRepository.findById(id);
                if (opt.isPresent()) {
                    evento evento = opt.get();
//                    if ("pendiente".equals(evento.getSolicitud_estado())) {
//                        solicitudes.setSolicitud_estado("aprobado");
//                        solicitudesRepository.save(solicitudes);
//                        responseMap.put("id solicitud", id);
                    return ResponseEntity.ok(responseMap);
                }else {
                        responseMap.put("estado", "error");
                        responseMap.put("msg", "Debe enviar un ID válido");
                        return ResponseEntity.badRequest().body(responseMap);
                    }


                }

        } catch (NumberFormatException e) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "El ID de solicitud debe ser un número válido");
            return ResponseEntity.badRequest().body(responseMap);
        }
        return ResponseEntity.ok(responseMap);
    }


//
//    @ResponseBody
//    @GetMapping( "/product")
//    public List<Product> listarProductos(){
//        return productRepository.findAll();
//    }
////
//    @GetMapping("/{id}")
//    public String buscarxId(@RequestParam("id") int id, Model model) {
//
//
//
//        model.addAttribute("evento", daoEvento.buscarPorId(id));
//
//        return "product/vistaEvento";
//    }


//    @GetMapping("/new")
//    public String nuevoProductoFrm(@ModelAttribute("product") Product product, Model model) {
//        model.addAttribute("listaCategorias", categoryDao.listarCategorias());
//        model.addAttribute("listaProveedores", supplierDao.listarProveedores());
//        return "product/form";
//    }
//
//    @PostMapping("/save")
//    public String guardarProducto(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
//                                  Model model, RedirectAttributes attr) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("listaCategorias", categoryDao.listarCategorias());
//            model.addAttribute("listaProveedores", supplierDao.listarProveedores());
//            return "product/form";
//        } else {
//            String msg = "Producto " + (product.getId() == 0 ? "creado" : "actualizado") + " exitosamente";
//            attr.addFlashAttribute("msg", msg);
//            productDao.guardarProducto(product);
//            return "redirect:/product";
//        }
//    }
//
//    @GetMapping("/edit")
//    public String editarTransportista(Model model, @RequestParam("id") int id) {
//
//        Product productBuscar = productDao.obtenerProductoPorId(id);
//
//        if (productBuscar != null) {
//            model.addAttribute("product", productBuscar);
//            model.addAttribute("listaCategorias", categoryDao.listarCategorias());
//            model.addAttribute("listaProveedores", supplierDao.listarProveedores());
//            return "product/form";
//        } else {
//            return "redirect:/product";
//        }
//    }
//
//    @GetMapping("/delete")
//    public String borrarTransportista(Model model, @RequestParam("id") int id, RedirectAttributes attr) {
//
//        Product productBuscar = productDao.obtenerProductoPorId(id);
//
//        if (productBuscar != null) {
//            productDao.borrarProducto(id);
//            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
//        }
//        return "redirect:/product";
//
//    }
}
