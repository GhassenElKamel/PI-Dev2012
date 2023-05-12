<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class MainController extends AbstractController
{
    /**
     * @Route("/front", name="front")
     */
    public function index1(): Response
    {
        return $this->render('baseFront.html.twig');
    }

    /**
     * @Route("/back", name="back")
     */
    public function index2(): Response
    {
        return $this->render('baseBack.html.twig');
    }
}
