<?php

// autoload_static.php @generated by Composer

namespace Composer\Autoload;

class ComposerStaticInitb5ea5a6861a7b8d5f0ad0b94fe2c7fab
{
    public static $prefixesPsr0 = array (
        'S' => 
        array (
            'Slim' => 
            array (
                0 => __DIR__ . '/..' . '/slim/slim',
            ),
        ),
    );

    public static function getInitializer(ClassLoader $loader)
    {
        return \Closure::bind(function () use ($loader) {
            $loader->prefixesPsr0 = ComposerStaticInitb5ea5a6861a7b8d5f0ad0b94fe2c7fab::$prefixesPsr0;

        }, null, ClassLoader::class);
    }
}
